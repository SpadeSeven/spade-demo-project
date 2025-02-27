import asyncio
import logging

from website_extractor import WebsiteExtractor

# 配置日志级别
logging.basicConfig(
    level=logging.INFO,  # 可以改为 logging.DEBUG 获取更详细的日志
    format="%(asctime)s - %(levelname)s - %(filename)s:%(lineno)d - %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S",
)
logger = logging.getLogger(__name__)


class CommonExtractor:
    deppseek_extractor = WebsiteExtractor(
        api_key="sk-c032608c80d44d0085442b8370996d86",
        base_url="https://api.deepseek.com",  # Deepseek的API地址
        model="deepseek-chat",
    )

    silioflow_extractor = WebsiteExtractor(
        api_key="sk-rijqlrrzmnwnfhyahoenktuwcpsspclohjvkifdeaobsmgvq",
        base_url="https://api.siliconflow.cn/v1",  # Deepseek的API地址
        model="Pro/deepseek-ai/DeepSeek-V3",
    )

    aliyun_extractor = WebsiteExtractor(
        api_key="sk-fa5986a1422c491fbeae3cdf2768811b",
        base_url="https://dashscope.aliyuncs.com/compatible-mode/v1",  # Deepseek的API地址
        model="deepseek-v3",
    )


async def main():
    logger.info("启动网站信息提取程序")

    # 测试提取信息
    url = "https://www.samsung.com.cn/"
    target_info = "提取公司的发展历程"

    logger.info(f"开始提取信息 - URL: {url}, 目标: {target_info}")

    try:
        result = await CommonExtractor.deppseek_extractor.extract_info(url, target_info)
        if result:
            logger.info(f"成功找到信息：{result}")
        else:
            logger.warning("未找到相关信息")
    except Exception as e:
        logger.error(f"程序执行出错: {str(e)}")


if __name__ == "__main__":
    asyncio.run(main())
