import csv
import json
import os
import re

from bs4 import BeautifulSoup

from src.utils import str_utils


def parse_qualification_approval(doc_data):
    """
    解析金融机构任职资格批复文件

    Args:
        doc_data: 原始文档数据

    Returns:
        dict: 解析后的结构化数据，如果解析失败返回None
    """
    if not doc_data or not isinstance(doc_data, dict):
        return None

    result = {
        "文号": doc_data.get("documentNo"),
        "文件标题": doc_data.get("docTitle"),
        "发文日期": doc_data.get("builddate"),
        "批复机构": "国家金融监督管理总局",
        "申请文号": None,
        "申请人": None,
        "申请职位": None,
        "批复结果": None,
        "到任要求": None,
        "主要内容": [],
        "原文链接": None,
        "文档来源": doc_data.get("docSource"),
        "索引号": doc_data.get("indexNo"),
        "机构类型": doc_data.get("agencyTypeName"),
        "发布日期": doc_data.get("publishDate"),
        "更新日期": doc_data.get("docEditdate"),
        "文档ID": doc_data.get("docId"),
        "状态码": doc_data.get("rptCode"),
        "状态信息": doc_data.get("msg"),
    }

    # 处理文档URL
    doc_file_url = doc_data.get("docFileUrl")
    pdf_file_url = doc_data.get("pdfFileUrl")
    result["原文链接"] = doc_file_url or pdf_file_url

    # 解析正文内容
    doc_content = doc_data.get("docClob", "")
    if not doc_content:
        return result

    try:
        # 使用BeautifulSoup解析HTML内容
        soup = BeautifulSoup(doc_content, "html.parser")
        text = soup.get_text(separator="\n").strip()

        # 提取申请文号
        application_no = re.search(r"[^（]*?银发〔(\d{4})\](\d+)号", text)
        if application_no:
            result["申请文号"] = (
                f"浙商银发〔{application_no.group(1)}〕{application_no.group(2)}号"
            )

        # 提取申请人和申请职位
        position_match = re.search(r"核准(\w+)(.*?独立董事)的?任职资格", text)
        if position_match:
            result["申请人"] = position_match.group(1)
            result["申请职位"] = position_match.group(2).strip()
            result["批复结果"] = "核准"

        # 提取到任要求
        arrival_match = re.search(r"自本行政许可决定作出之日起(\d+)个月内到任", text)
        if arrival_match:
            result["到任要求"] = (
                f"自本行政许可决定作出之日起{arrival_match.group(1)}个月内到任"
            )

        # 提取主要内容
        paragraphs = [p.strip() for p in text.split("\n") if p.strip()]
        main_points = []
        for p in paragraphs:
            if re.match(r"^[一二三]、", p):
                main_points.append(p)
        result["主要内容"] = main_points

    except Exception as e:
        print(f"解析文档内容时出错: {str(e)}")

    return result


def parse_nfra_detail_json(json_data):
    """
    解析完整的NFRA详情JSON文件

    Args:
        json_data: 原始JSON数据

    Returns:
        dict: 解析后的结构化数据，解析失败返回None
    """
    try:
        if not json_data or not isinstance(json_data, dict):
            return None

        # 检查基本结构
        if json_data.get("rptCode") != 200:
            print(f"API返回错误: {json_data.get('msg')}")
            return None

        doc_data = json_data.get("data")
        if not doc_data:
            print("JSON数据中缺少data字段")
            return None

        # 提取data层的所有字段
        result = {
            "rptCode": json_data.get("rptCode"),
            "msg": json_data.get("msg"),
            "docId": doc_data.get("docId"),
            "docTitle": doc_data.get("docTitle").replace("\n", " ").strip(),
            "docSubtitle": doc_data.get("docSubtitle").replace("\n", " ").strip(),
            "publishDate": doc_data.get("publishDate"),
            "docSummary": doc_data.get("docSummary"),
            "indexNo": doc_data.get("indexNo"),
            "documentNo": doc_data.get("documentNo"),
            "agencyType": doc_data.get("agencyType"),
            "agencyTypeName": doc_data.get("agencyTypeName"),
            "docEditdate": doc_data.get("docEditdate"),
            "documentType": doc_data.get("documentType"),
            "builddate": doc_data.get("builddate"),
            "interviewTypeName": doc_data.get("interviewTypeName"),
            "docSource": doc_data.get("docSource"),
            "docUuid": doc_data.get("docUuid"),
            "isTitleLink": doc_data.get("isTitleLink"),
            "titleLink": doc_data.get("titleLink"),
            "generaltype": doc_data.get("generaltype"),
            "docFileUrl": doc_data.get("docFileUrl"),
            "pdfFileUrl": doc_data.get("pdfFileUrl"),
            "caption": doc_data.get("caption"),
            "aviCodeUrl": doc_data.get("aviCodeUrl"),
            "listTwoItem": [],
            "docClob": None,
            "attachmentInfoVOList": doc_data.get("attachmentInfoVOList", []),
            "docImageInfoVOList": doc_data.get("docImageInfoVOList", []),
            "datafrom": doc_data.get("datafrom"),
            "ifShowShare": doc_data.get("ifShowShare"),
        }

        # 提取listTwoItem中的ItemName
        list_two_items = doc_data.get("listTwoItem", [])
        if list_two_items:
            result["listTwoItem"] = [
                item.get("ItemName") for item in list_two_items if item.get("ItemName")
            ]

        # 清洗docClob文本
        doc_clob = doc_data.get("docClob")
        if doc_clob:
            result["docClob"] = str_utils.extract_html_text(doc_clob)

        return result

    except Exception as e:
        print(f"解析JSON文件时出错: {str(e)}")
        return None


def save_to_csv(data, output_file, is_first=False):
    """
    将解析结果保存为CSV文件

    Args:
        data: 解析后的数据字典
        output_file: 输出文件路径
        is_first: 是否第一次写入（用于写入表头）
    """
    try:
        # 确保输出目录存在
        os.makedirs(os.path.dirname(output_file), exist_ok=True)

        # 准备CSV数据
        headers = list(data.keys())
        row = list(data.values())

        # 特殊处理listTwoItem列表
        list_two_idx = headers.index("listTwoItem")
        row[list_two_idx] = "|".join(row[list_two_idx]) if row[list_two_idx] else ""

        # 写入CSV文件
        with open(output_file, "a", newline="", encoding="utf-8") as f:
            writer = csv.writer(f)
            if is_first:
                writer.writerow(headers)
            writer.writerow(row)

    except Exception as e:
        print(f"保存CSV文件时出错: {str(e)}")


def save_to_txt(data, output_file):
    """
    将解析结果保存为TXT文件

    Args:
        data: 解析后的数据字典
        output_file: 输出文件路径
    """
    try:
        # 确保输出目录存在
        os.makedirs(os.path.dirname(output_file), exist_ok=True)

        # 准备写入内容
        content = []
        for key, value in data.items():
            if isinstance(value, list):
                value = "|".join(str(v) for v in value)
            content.append(f"{key}: {value}")

        # 写入TXT文件
        with open(output_file, "w", encoding="utf-8") as f:
            f.write("\n".join(content))

    except Exception as e:
        print(f"保存TXT文件时出错: {str(e)}")


def main():
    """主函数"""
    try:
        # 定义输入和输出路径
        input_dir = "output/nfra/detail"
        output_csv = "output/nfra/nfra_details_summary.csv"

        # 确保输入目录存在
        if not os.path.exists(input_dir):
            print(f"输入目录不存在: {input_dir}")
            return

        # 删除已存在的输出文件
        if os.path.exists(output_csv):
            os.remove(output_csv)

        # 处理所有JSON文件
        is_first = True
        for filename in os.listdir(input_dir):
            if filename.endswith(".json"):
                json_path = os.path.join(input_dir, filename)
                try:
                    # 读取JSON文件
                    with open(json_path, "r", encoding="utf-8") as f:
                        json_data = json.load(f)

                    # 解析文件
                    result = parse_nfra_detail_json(json_data)
                    if result:
                        # 保存到汇总CSV文件
                        save_to_csv(result, output_csv, is_first)
                        if is_first:
                            is_first = False
                        print(f"已处理: {filename}")
                    else:
                        print(f"解析失败: {filename}")

                except Exception as e:
                    print(f"处理文件 {filename} 时出错: {str(e)}")

        print(f"所有解析结果已保存到: {output_csv}")

    except Exception as e:
        print(f"程序执行出错: {str(e)}")


if __name__ == "__main__":
    main()
