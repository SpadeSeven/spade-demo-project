# -*- coding: utf-8 -*-
import logging
import time
from typing import Optional, Dict, Any

import requests
from firecrawl import FirecrawlApp

import crawl_parse

app = FirecrawlApp(api_url="http://localhost:3002")

# 设置日志
logging.basicConfig(
    level=logging.INFO, format="%(asctime)s - %(name)s - %(levelname)s - %(message)s"
)
logger = logging.getLogger(__name__)


def crawl(company_name: str, url: str) -> Optional[Dict[str, Any]]:
    """
    使用本地爬虫服务爬取网站内容

    Args:
        company_name: 公司名称
        url: 要爬取的网站URL

    Returns:
        Optional[Dict]: 爬取的结果数据，如果失败返回None
    """
    try:
        # 构建请求数据
        payload = {
            "url": url,
            "limit": 5,
            "scrapeOptions": {"formats": ["markdown", "html"]},
        }

        # 发送爬取请求
        logger.info(f"开始爬取 {company_name} 的网站: {url}")
        response = requests.post("http://localhost:3002/v1/crawl", json=payload)
        response.raise_for_status()

        # 解析初始响应
        result = response.json()
        if not result.get("success"):
            logger.error(f"爬取失败: {result}")
            return None

        crawl_id = result.get("id")
        if not crawl_id:
            logger.error("未获取到爬取任务ID")
            return None

        # 轮询获取爬取结果
        time.sleep(10)
        result_url = f"http://localhost:3002/v1/crawl/{crawl_id}"
        max_retries = 10
        retry_count = 0

        while retry_count < max_retries:
            logger.info(f"正在获取爬取结果，第 {retry_count + 1} 次尝试")
            result_response = requests.get(result_url)
            result_response.raise_for_status()

            crawl_result = result_response.json()
            if crawl_result.get("status") == "completed":
                crawl_parse.process_json(
                    company_name=company_name, json_content=result_response.content
                )
                logger.info(f"成功获取 {company_name} 的爬取结果")

                return crawl_result

            time.sleep(2)  # 等待2秒后重试
            retry_count += 1

        logger.error("获取爬取结果超时")
        return None

    except Exception as e:
        logger.error(f"爬取过程发生错误: {str(e)}")
        return None


if __name__ == "__main__":
    # 测试代码
    test_url = "https://www.fortunevc.com/"
    result = crawl("测试公司", test_url)
