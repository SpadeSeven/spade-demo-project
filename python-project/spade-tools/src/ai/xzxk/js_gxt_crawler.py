import requests
from bs4 import BeautifulSoup
import pandas as pd
from datetime import datetime
import time

class JSGXTCrawler:
    def __init__(self):
        self.base_url = "https://gxt.jiangsu.gov.cn/module/search/index.jsp"
        self.headers = {
            "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8",
            "Accept-Language": "zh-CN,zh;q=0.9,en;q=0.8",
            "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36"
        }

    def get_page_data(self, page=1):
        params = {
            "field": "vc_name:1",
            "i_columnid": "style_291",
            "vc_name": "",
            "currpage": str(page)
        }
        
        try:
            response = requests.get(self.base_url, params=params, headers=self.headers)
            response.raise_for_status()
            soup = BeautifulSoup(response.text, 'html.parser')
            
            records = []
            rows = soup.select("tr.bgc")
            
            for row in rows:
                cols = row.find_all('td')
                record = {
                    "项目名称": cols[0].get_text(strip=True),
                    "行政许可决定书文号": cols[2].get_text(strip=True),
                    "行政相对人名称": cols[4].get_text(strip=True),
                    "决定日期": cols[6].get_text(strip=True),
                    "许可机关": cols[8].get_text(strip=True),
                    "详情链接": "https://gxt.jiangsu.gov.cn/" + cols[0].find('a')['href'] if cols[0].find('a') else ""
                }
                records.append(record)
                
            return records
            
        except Exception as e:
            print(f"获取第{page}页数据时出错: {str(e)}")
            return []

    def crawl_all_data(self, start_page=1, end_page=81):
        all_records = []
        
        for page in range(start_page, end_page + 1):
            print(f"正在采集第{page}页...")
            records = self.get_page_data(page)
            all_records.extend(records)
            time.sleep(1)  # 添加延时，避免请求过快
            
        # 保存为CSV文件
        df = pd.DataFrame(all_records)
        filename = f"js_gxt_xzxk_{datetime.now().strftime('%Y%m%d')}.csv"
        df.to_csv(filename, index=False, encoding='utf-8-sig')
        print(f"数据已保存到{filename}")
        
        return all_records

if __name__ == "__main__":
    crawler = JSGXTCrawler()
    # 可以设置采集的起始页和结束页
    crawler.crawl_all_data(start_page=1, end_page=2) 