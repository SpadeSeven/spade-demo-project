import csv
import os
from pathlib import Path

from company_info import CompanyInfo


def process_company_details():
    """处理所有公司详情页面并生成CSV文件"""
    # 设置输入和输出路径
    input_dir = Path(
        "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/rank/cbinsights/detail"
    )
    output_dir = Path(
        "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/rank/cbinsights/"
    )
    output_file = output_dir / "company_details.csv"

    # 存储所有公司信息
    new_companies = []

    # 读取 unicorn_companies_20250131.csv
    csv_path = output_dir / "unicorn_companies_20250131.csv"
    with open(csv_path, "r", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        companies = list(reader)
        for company in companies:
            url = company["url"]
            url_name = url.split("/")[-1]
            # 判断html文件是否存在
            html_path = os.path.join(input_dir, url_name + ".html")
            if not os.path.exists(html_path):
                print(f"文件不存在: {html_path}")
                continue
            with open(html_path, "r", encoding="utf-8") as f2:
                html_content = f2.read()
                # 解析公司信息
                new_company = CompanyInfo.from_html(html_content)
                print(f"成功处理: {url_name}")
            # 将new_company的信息合并到company中
            company.update(new_company.to_dict())
            print(company)
            new_companies.append(company)

    if new_companies:
        # 保存到CSV文件
        fieldnames = new_companies[0].keys()
        with open(output_file, "w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(new_companies)

        print(f"\n处理完成！共处理 {len(companies)} 家公司")
        print(f"CSV文件已保存至: {output_file}")
    else:
        print("未找到任何公司信息")


if __name__ == "__main__":
    process_company_details()
