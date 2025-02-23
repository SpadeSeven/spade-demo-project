import asyncio
import logging
import random
import time
from typing import List, Optional

from firecrawl import FirecrawlApp
from openai import AsyncOpenAI
from openai import RateLimitError

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S",
)
logger = logging.getLogger(__name__)


class WebsiteExtractor:
    def __init__(self, api_key: str, base_url: str, model: str):
        logger.info("初始化 WebsiteExtractor")
        self.client = AsyncOpenAI(
            api_key=api_key,
            base_url=base_url,  # Deepseek的API地址
        )
        self.visited_urls = set()
        self.max_depth = 3
        self.app = FirecrawlApp(api_key="fc-0103a5df360e4af8b5d0cbf403a9be2c")
        self.last_api_call = 0
        self.min_interval = 5.0  # 最小请求间隔（秒）
        self.max_retries = 10  # 最大重试次数
        self.model = model  # 模型名称使用变量
        logger.debug(f"设置最大爬取深度: {self.max_depth}")
        logger.debug(f"设置模型名称: {self.model}")

    async def extract_info(self, url: str, target_info: str) -> Optional[str]:
        """
        从网站提取指定信息
        :param url: 网站URL
        :param target_info: 需要提取的信息描述
        :return: 提取到的信息，如果没找到返回None
        """
        logger.info(f"开始提取信息 - URL: {url}, 目标信息: {target_info}")
        return await self._crawl_and_extract(url, target_info, depth=0)

    async def _crawl_and_extract(
        self, url: str, target_info: str, depth: int
    ) -> Optional[str]:
        if url in self.visited_urls:
            logger.debug(f"URL已访问过，跳过: {url}")
            return None

        if depth >= self.max_depth:
            logger.debug(f"达到最大深度 {self.max_depth}，停止爬取: {url}")
            return None

        self.visited_urls.add(url)

        try:
            content = self.app.scrape_url(url)
            page_title = await self._get_page_title(content)
            logger.info(f"正在爬取页面 (深度 {depth}) - {url} - 标题: {page_title}")
            logger.debug(f"成功获取页面内容，长度: {len(content)}")
        except Exception as e:
            logger.error(f"爬取页面失败: {url}, 错误: {str(e)}")
            return None

        has_info = await self._check_content(content, target_info)
        if has_info:
            logger.info(f"在页面中找到目标信息: {url} - 标题: {page_title}")
            return await self._extract_content(content, target_info)

        logger.debug(
            f"页面中未找到目标信息，获取下一级链接: {url} - 标题: {page_title}"
        )
        next_urls = await self._get_relevant_links(content, target_info)
        logger.info(f"找到 {len(next_urls)} 个相关链接")

        for next_url in next_urls:
            result = await self._crawl_and_extract(next_url, target_info, depth + 1)
            if result:
                return result

        return None

    async def _wait_for_rate_limit(self):
        """确保API调用间隔符合限制"""
        now = time.time()
        elapsed = now - self.last_api_call
        if elapsed < self.min_interval:
            wait_time = self.min_interval - elapsed
            logger.debug(f"等待 {wait_time:.2f} 秒以符合速率限制")
            await asyncio.sleep(wait_time)
        self.last_api_call = time.time()

    async def _chat_completion(self, prompt: str) -> str:
        """调用LLM获取回复，包含重试机制"""
        retries = 0
        while retries < self.max_retries:
            try:
                await self._wait_for_rate_limit()
                logger.debug("调用 LLM API")
                response = await self.client.chat.completions.create(
                    model=self.model,
                    messages=[{"role": "user", "content": prompt}],
                    temperature=0.1,
                )
                return response.choices[0].message.content
            except RateLimitError as e:
                logger.warning(f"遇到速率限制: {str(e)}")
                retries += 1
                if retries >= self.max_retries:
                    logger.error(f"达到最大重试次数 ({self.max_retries})，放弃请求")
                    raise
                wait_time = (2**retries) + (
                    random.random() * 0.1
                )  # 指数退避 + 随机抖动
                logger.warning(
                    f"遇到速率限制，等待 {wait_time:.2f} 秒后重试 (第 {retries} 次)"
                )
                await asyncio.sleep(wait_time)
            except Exception as e:
                logger.error(f"LLM API 调用失败: {str(e)}")
                raise

    async def _check_content(self, content: str, target_info: str) -> bool:
        """检查页面内容是否包含目标信息"""
        logger.debug("检查页面是否包含目标信息")
        prompt = f"""页面内容：{content}
请判断是否包含以下信息：{target_info}
判断标准：
1. 如果页面包含指向更专门介绍该信息的链接（如"关于我们"、"公司简介"等），应返回"否"，让系统继续访问这些专门的页面
2. 如果当前页面直接包含完整的目标信息，返回"是"
3. 如果页面只包含简短或不完整的信息，返回"否"
只需回答是或否。"""
        response = await self._chat_completion(prompt)
        result = "是" in response
        logger.debug(f"检查结果: {'包含' if result else '不包含'}")
        return result

    async def _extract_content(self, content: str, target_info: str) -> str:
        """从页面提取目标信息"""
        logger.debug("开始提取目标信息")
        prompt = f"""页面内容：{content}
请提取以下信息：{target_info}
要求：
1. 提取完整的信息，不要简略
2. 如果有多处提到该信息，优先使用最详细的描述
3. 保持原文的重要表述，但可以适当整理格式
请直接返回提取的内容。"""
        result = await self._chat_completion(prompt)
        logger.info("成功提取目标信息")
        return result

    async def _get_page_title(self, content: str) -> str:
        """从页面内容中提取标题或主题"""
        try:
            prompt = (
                """请从页面内容中提取这个页面的标题或主题。要求：
1. 如果有明确的页面标题，直接返回标题
2. 如果没有明确标题，总结页面的主题（不超过10个字）
3. 直接返回结果，不要包含任何解释

页面内容："""
                + content
            )

            title = await self._chat_completion(prompt)
            # 清理可能的多余内容
            title = title.strip().strip('"').strip("'")
            return title
        except Exception as e:
            logger.error(f"提取页面标题失败: {str(e)}")
            return "未知页面"

    async def _get_relevant_links(self, content: str, target_info: str) -> List[str]:
        """获取可能包含目标信息的相关链接"""
        logger.debug("分析页面获取相关链接")
        prompt = f"""页面内容：{content}
目标信息：{target_info}
请分析并返回可能包含该信息的页面链接。优先级如下：
1. 最高优先级：专门介绍该信息的页面（如查找公司简介时优先返回"关于我们"、"公司简介"等页面的链接）
2. 次高优先级：包含该信息的子版块或子页面
3. 最低优先级：其他可能相关的页面

请返回一个JSON对象数组，每个对象包含url和title两个字段。按优先级排序。
示例格式：[{{"url": "http://example.com/about", "title": "关于我们"}}, ...]
注意：直接返回JSON，不要包含markdown格式。"""
        response = await self._chat_completion(prompt)
        try:
            # 处理可能的markdown格式
            if "```" in response:
                response = response.split("```")[1]
                if response.startswith("json"):
                    response = response[4:].strip()

            import json

            link_objects = json.loads(response)
            logger.debug(f"解析到 {len(link_objects)} 个相关链接")

            # 处理链接和记录标题
            links = []
            for link_obj in link_objects:
                url = link_obj.get("url", "")
                title = link_obj.get("title", "未知页面")
                if url:
                    abs_url = self._ensure_absolute_url(
                        url, link_objects[0]["url"] if link_objects else ""
                    )
                    links.append(abs_url)
                    logger.debug(f"发现相关链接: {abs_url} - 标题: {title}")

            return links
        except json.JSONDecodeError as e:
            logger.error(f"解析链接JSON失败: {str(e)}")
            logger.error(f"原始响应: {response}")
            return []
        except Exception as e:
            logger.error(f"获取相关链接时发生错误: {str(e)}")
            logger.error(f"原始响应: {response}")
            return []

    def _ensure_absolute_url(self, url: str, base_url: str) -> str:
        """确保URL是完整的绝对路径"""
        from urllib.parse import urljoin, urlparse

        if not url:
            return ""

        # 如果是相对路径，转换为绝对路径
        if not bool(urlparse(url).netloc):
            return urljoin(base_url, url)
        return url
