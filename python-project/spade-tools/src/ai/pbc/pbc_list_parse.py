from bs4 import BeautifulSoup
import csv
import os
from pathlib import Path

def parse_page(html_content):
    """解析单个页面内容,提取标题、URL、日期信息"""
    soup = BeautifulSoup(html_content, 'html.parser')
    items = []
    
    # 查找所有包含文章信息的表格
    tables = soup.find_all('table', width="90%", cellspacing="0", cellpadding="0")
    
    for table in tables:
        # 跳过表头
        if not table.find('td', class_="hei12jj"):
            continue
            
        try:
            # 提取链接和标题
            link = table.find('a')
            if not link:
                continue
                
            url = link.get('href', '')
            if url.startswith('/'):
                url = f"http://shanghai.pbc.gov.cn{url}"
                
            title = link.get('title', '').strip()
            
            # 提取日期
            date_td = table.find('td', width="100", class_="hei12jj")
            date = date_td.text.strip() if date_td else ''
            
            if title and url and date:
                items.append({
                    'title': title,
                    'url': url,
                    'date': date
                })
        except Exception as e:
            print(f"解析出错: {e}")
            continue
            
    return items

def get_page_number(filename):
    """从文件名中提取页码"""
    try:
        return int(filename.split('page_')[1].split('.')[0])
    except:
        return 0

def main():
    # 设置输入输出路径
    input_dir = Path('output/pbc/page')
    output_dir = Path('output/pbc')
    output_file = output_dir / 'pbc_policies.csv'
    
    # 确保输出目录存在
    output_dir.mkdir(parents=True, exist_ok=True)
    
    all_items = []
    
    # 遍历所有HTML文件
    for html_file in input_dir.glob('page_*.html'):
        try:
            page_num = get_page_number(html_file.name)
            
            with open(html_file, 'r', encoding='utf-8') as f:
                html_content = f.read()
                
            items = parse_page(html_content)
            
            # 添加页码信息
            for item in items:
                item['page'] = page_num
                all_items.append(item)
                
        except Exception as e:
            print(f"处理文件 {html_file} 时出错: {e}")
            continue
    
    # 按日期排序
    all_items.sort(key=lambda x: x['date'], reverse=True)
    
    # 写入CSV文件
    with open(output_file, 'w', encoding='utf-8-sig', newline='') as f:
        writer = csv.DictWriter(f, fieldnames=['title', 'url', 'date', 'page'])
        writer.writeheader()
        writer.writerows(all_items)
        
    print(f"已提取 {len(all_items)} 条政策信息并保存到 {output_file}")

if __name__ == '__main__':
    main()
