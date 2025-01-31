import csv
import time
from pathlib import Path

import requests


def get_headers():
    """返回请求头"""
    return {
        "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
        "accept-language": "zh-CN,zh;q=0.9",
        "cache-control": "no-cache",
        "pragma": "no-cache",
        "sec-ch-ua": '"Not A(Brand";v="8", "Chromium";v="132", "Google Chrome";v="132"',
        "sec-ch-ua-mobile": "?0",
        "sec-ch-ua-platform": '"macOS"',
        "sec-fetch-dest": "document",
        "sec-fetch-mode": "navigate",
        "sec-fetch-site": "none",
        "sec-fetch-user": "?1",
        "upgrade-insecure-requests": "1",
        "user-agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36",
    }


def download_company_details():
    """下载所有公司详细信息"""
    # 创建输出目录
    output_dir = Path("output/rank/cbinsights/detail")
    output_dir.mkdir(parents=True, exist_ok=True)

    # 读取CSV文件
    csv_path = "output/rank/cbinsights/unicorn_companies_20250131.csv"

    with open(csv_path, "r", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        companies = list(reader)

    # 下载每个公司的详细信息
    for i, company in enumerate(companies, 1):
        url = company["url"]
        company_name = url.split("/")[-1]
        output_file = output_dir / f"{company_name}.html"

        # 如果文件已存在则跳过
        if output_file.exists():
            print(f"跳过已下载的公司 ({i}/{len(companies)}): {company_name}")
            continue

        try:
            print(f"正在下载 ({i}/{len(companies)}): {company_name}")
            response = requests.get(url, headers=get_headers())
            response.raise_for_status()

            # 保存响应内容
            with open(output_file, "w", encoding="utf-8") as f:
                f.write(response.text)

            # 添加延迟以避免请求过快
            time.sleep(2)

        except Exception as e:
            print(f"下载失败 {company_name}: {str(e)}")

        # 每下载50个公司暂停30秒
        if i % 50 == 0:
            print(f"已下载{i}个公司,暂停30秒...")
            time.sleep(30)


if __name__ == "__main__":
    download_company_details()
