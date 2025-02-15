import csv
import json


def parse_company_data(company):
    """解析单个公司数据"""
    properties = company.get("properties", {})

    # 获取基本信息
    identifier = properties.get("identifier", {})
    website = properties.get("website", {}).get("value", "")
    description = properties.get("description", "")
    phone = properties.get("phone_number", "")
    email = properties.get("contact_email", "")
    facebook = properties.get("facebook", {}).get("value", "")
    growth = properties.get("growth_insight_indicator", "")

    # 获取创始人列表
    founders = [f.get("value", "") for f in properties.get("founder_identifiers", [])]
    founders_str = "|".join(founders)

    # 获取类别列表
    categories = [c.get("value", "") for c in properties.get("categories", [])]
    categories_str = "|".join(categories)

    # 获取位置信息
    locations = [
        location_identifier.get("value", "")
        for location_identifier in properties.get("location_identifiers", [])
    ]
    locations_str = "|".join(locations)

    return {
        "uuid": company.get("uuid", ""),
        "name": identifier.get("value", ""),
        "permalink": identifier.get("permalink", ""),
        "website": website,
        "description": description,
        "phone": phone,
        "email": email,
        "facebook": facebook,
        "growth_indicator": growth,
        "founders": founders_str,
        "categories": categories_str,
        "locations": locations_str,
    }


def main():
    # 读取JSON文件
    with open("src/rank/crunchbase/companies.json", "r", encoding="utf-8") as f:
        data = json.load(f)

    # 获取所有公司数据
    companies = data.get("entities", [])

    # 定义CSV表头
    fieldnames = [
        "uuid",
        "name",
        "permalink",
        "website",
        "description",
        "phone",
        "email",
        "facebook",
        "growth_indicator",
        "founders",
        "categories",
        "locations",
    ]

    # 写入CSV文件
    with open("companies_output.csv", "w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()

        for company in companies:
            company_data = parse_company_data(company)
            writer.writerow(company_data)


if __name__ == "__main__":
    main()
