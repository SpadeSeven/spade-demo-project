import json
import math
import os
import time

import requests

from src.utils.logger import LoggerManager

# 初始化日志系统
config_path = os.path.join(
    os.path.dirname(__file__), "..", "..", "..", "config", "config.yaml"
)
LoggerManager().init_logger(config_path)
logger = LoggerManager.get_logger()


class NFRALicenseCrawler:
    def __init__(self):
        self.base_url = (
            "https://www.nfra.gov.cn/cbircweb/DocInfo/SelectDocByItemIdAndChild"
        )
        self.headers = {
            "Accept": "*/*",
            "Accept-Language": "zh-CN,zh;q=0.9,en;q=0.8",
            "Cache-Control": "no-cache",
            "Connection": "keep-alive",
            "Referer": "https://www.nfra.gov.cn/cn/view/pages/ItemList.html?itemPId=923&itemId=4110",
            "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36",
            "X-Requested-With": "XMLHttpRequest",
        }
        self.output_dir = "output/nfra/page"
        self.page_size = 18
        os.makedirs(self.output_dir, exist_ok=True)

    def get_total_pages(self) -> int:
        """获取总页数"""
        try:
            params = {"itemId": "4110", "pageSize": self.page_size, "pageIndex": 1}
            response = requests.get(self.base_url, params=params, headers=self.headers)
            response.raise_for_status()
            data = response.json()
            total_items = data["data"]["total"]
            total_pages = math.ceil(total_items / self.page_size)
            logger.info(f"总记录数: {total_items}, 总页数: {total_pages}")
            return total_pages
        except Exception as e:
            logger.error(f"获取总页数失败: {str(e)}")
            return 0

    def fetch_and_save_page(self, page: int) -> bool:
        """获取指定页面的数据并保存"""
        params = {"itemId": "4110", "pageSize": self.page_size, "pageIndex": page}
        output_file = os.path.join(self.output_dir, f"page_{page}.json")

        try:
            response = requests.get(self.base_url, params=params, headers=self.headers)
            response.raise_for_status()
            data = response.json()

            # 检查是否有数据
            if not data or not data.get("data"):
                logger.warning(f"第{page}页没有数据")
                return False

            # 保存JSON文件
            with open(output_file, "w", encoding="utf-8") as f:
                json.dump(data, f, ensure_ascii=False, indent=2)

            logger.info(f"成功保存第{page}页数据到: {output_file}")
            return True

        except Exception as e:
            logger.error(f"获取或保存第{page}页数据失败: {str(e)}")
            return False

    def crawl_all_pages(self, start_page: int = 1):
        """爬取所有页面的数据"""
        total_pages = self.get_total_pages()
        if total_pages == 0:
            logger.error("获取总页数失败，退出爬取")
            return

        logger.info(f"开始爬取，从第{start_page}页到第{total_pages}页")

        for page in range(start_page, 10 + 1):
            logger.info(f"正在获取第{page}/{total_pages}页数据")
            success = self.fetch_and_save_page(page)

            if not success:
                logger.warning(f"第{page}页获取失败，继续下一页")
                continue

            time.sleep(1)  # 添加延时，避免请求过于频繁


def main():
    crawler = NFRALicenseCrawler()
    crawler.crawl_all_pages(start_page=1)


if __name__ == "__main__":
    main()
