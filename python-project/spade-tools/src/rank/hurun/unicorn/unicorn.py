import logging
import math
import os
from time import sleep

import pandas as pd
import requests

# 配置日志
logging.basicConfig(
    level=logging.INFO, format="%(asctime)s - %(levelname)s - %(message)s"
)
logger = logging.getLogger(__name__)


class HurunUnicornCrawler:
    def __init__(self):
        self.base_url = "https://www.hurun.net/zh-CN/Rank/HsRankDetailsList"
        self.headers = {
            "accept": "application/json, text/javascript, */*; q=0.01",
            "accept-language": "zh-CN,zh;q=0.9,en;q=0.8",
            "cache-control": "no-cache",
            "content-type": "application/json",
            "pragma": "no-cache",
            "referer": "https://www.hurun.net/zh-CN/Rank/HsRankDetails?pagetype=unicorn",
            "user-agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36",
            "x-requested-with": "XMLHttpRequest",
        }
        self.params = {"num": "E9W1YX99", "search": "", "offset": 0, "limit": 20}
        self.output_dir = "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/rank/hurun/unicorn"

    def fetch_page(self, offset):
        """获取单页数据"""
        self.params["offset"] = offset
        try:
            response = requests.get(
                self.base_url, headers=self.headers, params=self.params
            )
            response.raise_for_status()
            return response.json()
        except Exception as e:
            logger.error(f"获取页面数据失败 offset={offset}: {str(e)}")
            return None

    def fetch_all_data(self):
        """获取所有页面数据"""
        logger.info("开始获取所有页面数据")
        all_rows = []
        first_page = self.fetch_page(0)

        if not first_page:
            logger.error("获取第一页数据失败")
            return []

        total = first_page["total"]
        total_pages = math.ceil(total / 20)
        logger.info(f"总记录数: {total}, 总页数: {total_pages}")

        all_rows.extend(first_page["rows"])

        # 获取剩余页面
        for page in range(1, total_pages):
            offset = page * 20
            logger.info(f"正在获取第 {page + 1} 页数据")
            page_data = self.fetch_page(offset)

            if page_data and "rows" in page_data:
                all_rows.extend(page_data["rows"])
            else:
                logger.error(f"获取第 {page + 1} 页数据失败")

            sleep(1)  # 添加延迟，避免请求过快

        logger.info(f"数据获取完成，共获取 {len(all_rows)} 条记录")
        return all_rows

    def process_data(self, rows):
        """处理数据并转换为DataFrame"""
        logger.info("开始处理数据")
        df = pd.DataFrame(rows)

        # 选择需要的列
        columns = {
            "hs_Rank_Unicorn_Year": "年份",
            "hs_Rank_Unicorn_Ranking": "排名",
            "hs_Rank_Unicorn_Ranking_Change": "排名变化",
            "hs_Rank_Unicorn_Wealth": "估值(亿元)",
            "hs_Rank_Unicorn_Wealth_USD": "估值(亿美元)",
            "hs_Rank_Unicorn_Wealth_Change": "估值变化(%)",
            "hs_Rank_Unicorn_ChaName_Cn": "创始人(中文)",
            "hs_Rank_Unicorn_ChaName_En": "创始人(英文)",
            "hs_Rank_Unicorn_ComName_Cn": "公司名称(中文)",
            "hs_Rank_Unicorn_ComName_En": "公司名称(英文)",
            "hs_Rank_Unicorn_ComHeadquarters_Cn": "总部(中文)",
            "hs_Rank_Unicorn_ComHeadquarters_En": "总部(英文)",
            "hs_Rank_Unicorn_Industry_Cn": "行业(中文)",
            "hs_Rank_Unicorn_Industry_En": "行业(英文)",
        }

        df = df[columns.keys()].rename(columns=columns)
        return df

    def save_to_csv(self, df):
        """保存数据到CSV文件"""
        if not os.path.exists(self.output_dir):
            os.makedirs(self.output_dir)

        output_file = os.path.join(self.output_dir, "hurun_unicorn_2024.csv")
        df.to_csv(output_file, index=False, encoding="utf-8-sig")
        logger.info(f"数据已保存到: {output_file}")

    def run(self):
        """运行爬虫程序"""
        rows = self.fetch_all_data()
        if rows:
            df = self.process_data(rows)
            self.save_to_csv(df)
        else:
            logger.error("未获取到数据")


if __name__ == "__main__":
    crawler = HurunUnicornCrawler()
    crawler.run()
