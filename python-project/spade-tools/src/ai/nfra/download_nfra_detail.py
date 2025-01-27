import csv
import json
import logging
import os
import requests
import time
from pathlib import Path

# 配置logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)

def get_doc_ids_from_csv(csv_path):
    """从CSV文件中提取docId"""
    doc_ids = []
    try:
        with open(csv_path, 'r', encoding='utf-8') as f:
            reader = csv.DictReader(f)
            for row in reader:
                if row.get('docId'):
                    doc_ids.append(row['docId'])
        logging.info(f"Successfully extracted {len(doc_ids)} docIds from CSV")
        return doc_ids
    except Exception as e:
        logging.error(f"Error reading CSV file: {e}")
        return []

def download_detail(doc_id, output_dir):
    """下载单个详情页"""
    url = f'https://www.nfra.gov.cn/cn/static/data/DocInfo/SelectByDocId/data_docId={doc_id}.json'
    
    headers = {
        'Accept': '*/*',
        'Accept-Language': 'zh-CN,zh;q=0.9',
        'Cache-Control': 'no-cache',
        'Connection': 'keep-alive',
        'Pragma': 'no-cache',
        'Referer': f'https://www.nfra.gov.cn/cn/view/pages/ItemDetail.html?docId={doc_id}&itemId=4110&generaltype=1',
        'Sec-Fetch-Dest': 'empty',
        'Sec-Fetch-Mode': 'cors',
        'Sec-Fetch-Site': 'same-origin',
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36',
        'X-Requested-With': 'XMLHttpRequest',
        'sec-ch-ua': '"Not A(Brand";v="8", "Chromium";v="132", "Google Chrome";v="132"',
        'sec-ch-ua-mobile': '?0',
        'sec-ch-ua-platform': '"macOS"'
    }

    try:
        response = requests.get(url, headers=headers)
        response.raise_for_status()
        
        # 保存响应内容
        output_path = os.path.join(output_dir, f'{doc_id}.json')
        with open(output_path, 'w', encoding='utf-8') as f:
            f.write(response.text)
        
        logging.info(f"Successfully downloaded detail for docId: {doc_id}")
        return True
    except Exception as e:
        logging.error(f"Error downloading detail for docId {doc_id}: {e}")
        return False

def main():
    # 创建输出目录
    output_dir = Path('output/nfra/detail')
    output_dir.mkdir(parents=True, exist_ok=True)
    
    # 获取所有docId
    csv_path = 'output/nfra/nfra_list.csv'
    doc_ids = get_doc_ids_from_csv(csv_path)
    
    if not doc_ids:
        logging.error("No docIds found in CSV file")
        return
    
    # 下载所有详情页
    success_count = 0
    for doc_id in doc_ids:
        if download_detail(doc_id, output_dir):
            success_count += 1
        time.sleep(1)  # 添加延迟，避免请求过快
    
    logging.info(f"Download completed. Successfully downloaded {success_count}/{len(doc_ids)} details")

if __name__ == '__main__':
    main()