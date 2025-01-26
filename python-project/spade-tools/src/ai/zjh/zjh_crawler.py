import json
import logging
import os
from math import ceil
from time import sleep

import requests


def setup_logger():
    """配置日志"""
    log_dir = "output/logs"
    if not os.path.exists(log_dir):
        os.makedirs(log_dir)

    logging.basicConfig(
        level=logging.INFO,
        format='%(asctime)s - %(levelname)s - %(message)s',
        handlers=[
            logging.FileHandler(os.path.join(log_dir, 'zjh_crawler.log'), encoding='utf-8'),
            logging.StreamHandler()
        ]
    )
    return logging.getLogger(__name__)


def create_output_dir():
    """创建输出目录"""
    output_dir = "output/zjh"
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)
    return output_dir


def get_total_pages(data):
    """从返回数据中获取总页数"""
    try:
        total_records = data['data']['total']
        page_size = data['data']['rows']
        total_pages = ceil(total_records / page_size)
        return total_pages
    except (KeyError, TypeError):
        return None


def fetch_page(page_num):
    """获取指定页码的数据"""
    url = f"http://www.csrc.gov.cn/searchList/d5483bfd719e4e8d95ffbe975b2f73ff"

    params = {
        "_isAgg": "true",
        "_isJson": "true",
        "_pageSize": "10",
        "_template": "index",
        "_rangeTimeGte": "",
        "_channelName": "",
        "page": str(page_num)
    }

    headers = {
        "accept": "*/*",
        "accept-language": "zh-CN,zh;q=0.9,en;q=0.8",
        "proxy-connection": "keep-alive",
        "x-requested-with": "XMLHttpRequest",
        "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36"
    }

    try:
        response = requests.get(url, params=params, headers=headers)
        response.raise_for_status()
        return response.json()
    except Exception as e:
        logger.error(f"获取第 {page_num} 页时发生错误", exc_info=e)
        return None


def main():
    output_dir = create_output_dir()
    page_num = 1
    total_pages = 10

    while True:
        logger.info(f"正在获取第 {page_num} 页...")
        data = fetch_page(page_num)

        if not data:
            logger.error("获取数据失败，程序终止")
            break

        # 获取总页数
        if total_pages is None:
            total_pages = get_total_pages(data)
            if total_pages:
                logger.info(f"总页数: {total_pages}")
            else:
                logger.error("无法获取总页数，程序终止")
                break

        # 保存数据到文件
        output_file = os.path.join(output_dir, f"page_{page_num}.json")
        with open(output_file, 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=2)

        logger.info(f"第 {page_num} 页数据已保存到 {output_file}")

        # 检查是否到达最后一页
        if page_num >= total_pages:
            logger.info("已到达最后一页")
            break

        page_num += 1
        sleep(1)  # 添加延时，避免请求过于频繁


def _get_cookie():
    url = f"http://www.csrc.gov.cn/"

    headers = {
        "accept": "*/*",
        "accept-language": "zh-CN,zh;q=0.9,en;q=0.8",
        "proxy-connection": "keep-alive",
        "x-requested-with": "XMLHttpRequest"
    }

    try:
        response = requests.get(url, headers=headers)
        response.raise_for_status()
        for key, value in response.cookies.items():
            print(key)
            print(value)
        return response.cookies
    except Exception as e:
        logger.error(f"获取cookie时发生错误", exc_info=e)
        return None


if __name__ == "__main__":
    logger = setup_logger()
    main()
    # _get_cookie()
