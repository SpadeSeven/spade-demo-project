from playwright.sync_api import sync_playwright
import json
import logging

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    datefmt='%Y-%m-%d %H:%M:%S'
)
logger = logging.getLogger(__name__)


def download_cbirc_announcements():
    """
    下载银保监会行政许可公告信息
    """
    logger.info("开始下载银保监会公告信息...")
    url = "https://www.cbirc.gov.cn/cn/view/pages/ItemList.html"
    params = {
        "itemPId": "923",
        "itemId": "4110",
        "itemUrl": "ItemListRightList.html",
        "itemName": "总局机关",
        "itemsubPId": "930",
        "itemsubPName": "行政许可"
    }

    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
    }

    try:
        with sync_playwright() as p:
            # 启动浏览器
            browser = p.chromium.launch(headless=True)  # headless=False 可以看到浏览器界面
            
            # 创建新页面
            page = browser.new_page()
            
            # 访问目标网站
            page.goto(url, params=params, headers=headers)
            
            # 等待特定元素出现
            page.wait_for_selector('.some-class')
            
            # 获取页面标题
            title = page.title()
            logger.info(f'页面标题: {title}')
            
            # 获取特定元素的文本
            # 例如获取所有段落文本
            items = page.query_selector_all('.right-item')
            logger.info(f"找到 {len(items)} 条公告")

            announcements = []

            for item in items:
                title = item.query_selector_one('.right-item-title').inner_text().strip()
                date = item.query_selector_one('.right-item-date').inner_text().strip()
                link = item.query_selector_one('a').get_attribute('href')

                announcement = {
                    "title": title,
                    "date": date,
                    "link": f"https://www.cbirc.gov.cn{link}" if link.startswith('/') else link
                }
                announcements.append(announcement)
                logger.debug(f"已解析公告: {title} ({date})")

            # 截图
            page.screenshot(path="screenshot.png")
            
            # 关闭浏览器
            browser.close()

        logger.info("公告信息下载完成")
        return announcements

    except Exception as e:
        logger.error(f"处理数据时发生错误: {str(e)}")
        return None


def save_announcements(announcements, filename='announcements.json'):
    """
    将公告信息保存到JSON文件
    """
    if announcements:
        try:
            with open(filename, 'w', encoding='utf-8') as f:
                json.dump(announcements, f, ensure_ascii=False, indent=2)
            logger.info(f"公告信息已成功保存到 {filename}")
        except Exception as e:
            logger.error(f"保存文件时发生错误: {str(e)}")
    else:
        logger.warning("没有公告信息可保存")


if __name__ == '__main__':
    announcements = download_cbirc_announcements()
    if announcements:
        save_announcements(announcements)
