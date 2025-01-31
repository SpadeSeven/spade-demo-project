import csv
import logging
import os
from datetime import datetime

from lxml import html


# 设置日志
def setup_logger():
    log_dir = "logs"
    if not os.path.exists(log_dir):
        os.makedirs(log_dir)

    log_file = os.path.join(
        log_dir, f"unicorn_download_{datetime.now().strftime('%Y%m%d')}.log"
    )

    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
        handlers=[logging.FileHandler(log_file), logging.StreamHandler()],
    )
    return logging.getLogger(__name__)


def download_page():
    logger = logging.getLogger(__name__)

    try:
        # 首先尝试从本地文件读取
        logger.info("Trying to read from local file...")
        with open(
            "src/rank/cbinsights/unicorn/list_of_unicorn_companies.html",
            "r",
            encoding="utf-8",
        ) as f:
            content = f.read()
            logger.info("Successfully read from local file")
            return content
    except Exception as e:
        logger.warning(f"Could not read from local file: {str(e)}")
        return None


def parse_unicorn_data(html_content):
    logger = logging.getLogger(__name__)

    try:
        # 先检查HTML内容
        if not html_content:
            logger.error("HTML content is empty")
            return []

        # 保存HTML内容用于调试
        debug_dir = "debug"
        if not os.path.exists(debug_dir):
            os.makedirs(debug_dir)
        with open(
            os.path.join(debug_dir, "downloaded_page.html"), "w", encoding="utf-8"
        ) as f:
            f.write(html_content)

        # 使用lxml解析HTML
        tree = html.fromstring(html_content)

        # 使用xpath定位表格行 - 修正xpath选择器
        rows = tree.xpath("//tr")  # 简化xpath选择器
        logger.info(f"Found {len(rows)} rows in table")

        companies = []
        for idx, row in enumerate(rows):
            try:
                # 使用xpath提取每个单元格的文本
                cols = row.xpath("./td")
                if len(cols) >= 7:
                    # 获取公司链接和名称
                    name_link = cols[0].xpath(".//a")
                    name = (
                        name_link[0].text.strip() if name_link else cols[0].text.strip()
                    )

                    # 获取估值（移除$和B符号）
                    valuation_text = cols[1].text.strip()
                    # 处理data-value属性
                    valuation = (
                        cols[1]
                        .get("data-value", valuation_text)
                        .replace("$", "")
                        .replace("B", "")
                    )

                    # 获取日期
                    date_joined = cols[2].text.strip()

                    # 获取其他信息
                    company = {
                        "name": name,
                        "valuation": valuation,
                        "date_joined": date_joined,
                        "country": cols[3].text.strip() if cols[3].text else "",
                        "city": cols[4].text.strip() if cols[4].text else "",
                        "industry": cols[5].text.strip() if cols[5].text else "",
                        "investors": cols[6].text.strip() if cols[6].text else "",
                    }

                    # 添加URL
                    if name_link:
                        company["url"] = name_link[0].get("href", "")

                    companies.append(company)

                    # 记录每个公司的解析信息
                    logger.debug(f"Parsed company: {name} with valuation {valuation}")

            except Exception as e:
                logger.error(f"Error parsing row {idx}: {str(e)}")
                continue

        logger.info(f"Successfully parsed {len(companies)} companies")

        # 验证数据
        if not companies:
            logger.warning("No companies were parsed from the table")
        else:
            # 输出前几个公司的信息用于验证
            logger.info("First few companies parsed:")
            for company in companies[:3]:
                logger.info(
                    f"Company: {company['name']}, Valuation: {company['valuation']}"
                )

        return companies
    except Exception as e:
        logger.error(f"Error parsing HTML content: {str(e)}")
        logger.error(f"HTML content length: {len(html_content) if html_content else 0}")
        return []


def save_to_csv(companies):
    logger = logging.getLogger(__name__)

    output_dir = "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/rank/cbinsights"
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    output_file = os.path.join(
        output_dir, f"unicorn_companies_{datetime.now().strftime('%Y%m%d')}.csv"
    )

    try:
        # 添加URL字段到输出
        fieldnames = [
            "name",
            "valuation",
            "date_joined",
            "country",
            "city",
            "industry",
            "investors",
            "url",
        ]

        with open(output_file, "w", newline="", encoding="utf-8") as f:
            writer = csv.DictWriter(f, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(companies)
        logger.info(f"Successfully saved data to {output_file}")

        # 验证输出文件
        with open(output_file, "r", encoding="utf-8") as f:
            line_count = sum(1 for line in f)
        logger.info(f"CSV file contains {line_count} lines (including header)")

    except Exception as e:
        logger.error(f"Error saving to CSV: {str(e)}")


def main():
    logger = setup_logger()
    logger.info("Starting unicorn companies data collection")

    html_content = download_page()
    if html_content:
        companies = parse_unicorn_data(html_content)
        if companies:
            save_to_csv(companies)

    logger.info("Finished unicorn companies data collection")


if __name__ == "__main__":
    main()
