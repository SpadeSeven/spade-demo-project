import json
import csv
import os
import re
from html import unescape
from bs4 import BeautifulSoup

def clean_html_text(html_content):
    """清理HTML内容，返回纯文本"""
    if not html_content:
        return ''
    
    # 使用BeautifulSoup解析HTML
    soup = BeautifulSoup(html_content, 'html.parser')
    
    # 获取纯文本内容
    text = soup.get_text(separator=' ', strip=True)
    
    # HTML实体解码
    text = unescape(text)
    
    # 清理特殊字符
    text = re.sub(r'\s+', ' ', text)  # 合并多个空白字符
    text = re.sub(r'&ensp;', ' ', text)  # 处理特殊空格
    text = text.strip()
    
    return text

def extract_license_info(input_file, output_file):
    # 创建output目录(如果不存在)
    os.makedirs('output', exist_ok=True)
    
    # 读取JSON文件
    with open(input_file, 'r', encoding='utf-8') as f:
        data = json.load(f)
    
    # 提取结果列表
    results = data['data']['results']
    
    # 准备CSV表头 - publicInfo字段
    public_info_fields = [
        '发文日期', '公开方式', '索引号', '公开范围', '分类', 
        '主题词', '分类2', '文件编号', '主站发布机构', 
        '文号', '发文单位', '发布机构', '公开时限'
    ]
    
    # basic字段
    basic_fields = [
        '部门', '来源', '派出机构名称', '主站来源', 
        '职位', '作者'
    ]
    
    # 其他字段
    other_fields = [
        '发布时间', '标题', '副标题', '内容', '原文链接',
        'channelName', 'channelId', 'manuscriptId'
    ]
    
    headers = public_info_fields + basic_fields + other_fields
    
    # 写入CSV文件
    with open(output_file, 'w', encoding='utf-8-sig', newline='') as f:
        writer = csv.writer(f)
        writer.writerow(headers)
        
        for item in results:
            # 初始化所有字段为空字符串
            row_data = {field: '' for field in headers}
            
            # 从domainMetaList中提取信息
            for meta in item['domainMetaList']:
                if meta['domainMetadataCode'] == 'publicInfo':
                    for result in meta['resultList']:
                        field_map = {
                            'fwrq': '发文日期',
                            'gkfs': '公开方式',
                            'syh': '索引号',
                            'gkfw': '公开范围',
                            'fl': '分类',
                            'ztc': '主题词',
                            'cesysj': '分类2',
                            'wjbh': '文件编号',
                            'zzfbjg': '主站发布机构',
                            'wh': '文号',
                            'fwdw': '发文单位',
                            'fbjg': '发布机构',
                            'gksx': '公开时限'
                        }
                        if result['key'] in field_map:
                            row_data[field_map[result['key']]] = clean_html_text(result['value'])
                            
                elif meta['domainMetadataCode'] == 'basic':
                    for result in meta['resultList']:
                        field_map = {
                            'section': '部门',
                            'infosource': '来源',
                            'pcjgs': '派出机构名称',
                            'homesource': '主站来源',
                            'zw': '职位',
                            'authors': '作者'
                        }
                        if result['key'] in field_map:
                            row_data[field_map[result['key']]] = clean_html_text(result['value'])
            
            # 提取其他字段
            row_data['发布时间'] = item.get('publishedTimeStr', '')
            row_data['标题'] = clean_html_text(item['title'])
            row_data['副标题'] = clean_html_text(item.get('subTitle', ''))
            row_data['内容'] = clean_html_text(item['content'])
            row_data['原文链接'] = item.get('url', '')
            row_data['channelName'] = item.get('channelName', '')
            row_data['channelId'] = item.get('channelId', '')
            row_data['manuscriptId'] = item.get('manuscriptId', '')
            
            # 按headers顺序写入数据
            writer.writerow([row_data[field] for field in headers])

if __name__ == '__main__':
    input_file = 'src/ai/zjh/zjt_list_detail.json'
    output_file = 'output/license_info.csv'
    extract_license_info(input_file, output_file)
    print(f'已将行政许可信息保存至: {output_file}') 