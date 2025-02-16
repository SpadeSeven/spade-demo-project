# -*- coding: utf-8 -*-
import csv
import json
import os
import shutil
import re


def remove_urls_from_markdown(markdown_content):
    # 使用正则表达式删除 Markdown 文本中的 URL
    return re.sub(r"http[s]?://\S+", "", markdown_content)


def process_json(company_name, json_content):
    # 创建文件夹路径
    base_output = "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/website/crawl/"
    company_folder = os.path.join(base_output, company_name)

    # 如果文件夹存在，先删除
    if os.path.exists(company_folder):
        shutil.rmtree(company_folder)

    # 创建新的文件夹
    os.makedirs(company_folder)

    # 解析 JSON 内容
    data = json.loads(json_content)

    # 写入 metadata 到 CSV 文件
    metadata_keys = [
        "title",
        "description",
        "favicon",
        "language",
        "keywords",
        "ogLocaleAlternate",
        "scrapeId",
        "viewport",
        "renderer",
        "author",
        "sourceURL",
        "url",
        "statusCode",
    ]

    csv_file_path = os.path.join(company_folder, "metadata.csv")
    with open(csv_file_path, mode="w", newline="", encoding="utf-8") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=metadata_keys)
        writer.writeheader()

        for item in data["data"]:
            metadata = item.get("metadata", {})
            row = {key: metadata.get(key, "") for key in metadata_keys}
            writer.writerow(row)

    # 创建 content 文件夹
    content_folder = os.path.join(company_folder, "content")
    os.makedirs(content_folder)

    # 写入 markdown 和 html 文件
    for index, item in enumerate(data["data"]):
        title = item["metadata"].get("title", "Untitled")
        markdown_content = item.get("markdown", "")
        html_content = item.get("html", "")

        # 写入 markdown 文件
        markdown_file_path = os.path.join(content_folder, f"{index + 1}_{title}.md")
        with open(markdown_file_path, "w", encoding="utf-8") as md_file:
            md_file.write(markdown_content)

        # 写入 markdown 文件
        markdown_clean_file_path = os.path.join(
            content_folder, f"{index + 1}_{title}_clean.md"
        )
        with open(markdown_clean_file_path, "w", encoding="utf-8") as md_file:
            md_file.write(remove_urls_from_markdown(markdown_content))

        # 写入 html 文件
        html_file_path = os.path.join(content_folder, f"{index + 1}_{title}.html")
        with open(html_file_path, "w", encoding="utf-8") as html_file:
            html_file.write(html_content)


if __name__ == "__main__":
    with open("crawl.json", "r", encoding="utf-8") as f:
        process_json("达晨财智", f.read())
