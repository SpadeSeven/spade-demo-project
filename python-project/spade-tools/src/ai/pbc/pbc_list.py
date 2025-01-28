import asyncio
from playwright.async_api import async_playwright
import logging
import os
import json
import re

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(filename)s - %(levelname)s - %(message)s',
    datefmt='%Y-%m-%d %H:%M:%S'
)
logger = logging.getLogger(__name__)


async def crawl_website(url, save_dir):
    """
    使用 Playwright 爬取网站并保存结果
    """
    # 从URL中提取页码
    page_match = re.search(r'index(\d+)\.html', url)
    page_num = page_match.group(1) if page_match else '1'
    
    # 确保保存目录存在
    os.makedirs(save_dir, exist_ok=True)
    
    try:
        async with async_playwright() as p:
            # 启动浏览器，设置更长的超时时间和其他选项
            browser = await p.chromium.launch(
                headless=True,
                args=['--disable-gpu', '--no-sandbox', '--disable-dev-shm-usage']
            )

            # 创建上下文并设置超时
            context = await browser.new_context(
                viewport={'width': 1920, 'height': 1080},
                user_agent='Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
            )

            # 创建新页面
            page = await context.new_page()

            # 设置更长的超时时间（2分钟）
            page.set_default_timeout(120000)

            logger.info(f"开始访问网站: {url}")

            # 访问网站
            try:
                response = await page.goto(url, wait_until='networkidle')
                if response:
                    logger.info(f"页面加载状态码: {response.status}")

                # 等待页面加载完成
                await page.wait_for_load_state('domcontentloaded')

                # 获取页面内容
                content = await page.content()
                title = await page.title()

                logger.info(f"成功获取页面内容，标题: {title}")

                # 使用页码命名文件
                html_file = os.path.join(save_dir, f'page_{page_num}.html')
                with open(html_file, 'w', encoding='utf-8') as f:
                    f.write(content)
                logger.info(f"HTML内容已保存到: {html_file}")

                # 保存截图
                screenshot_file = os.path.join(save_dir, f'screenshot_{page_num}.png')
                await page.screenshot(path=screenshot_file)
                logger.info(f"截图已保存到: {screenshot_file}")

                # 保存元数据
                metadata = {
                    'title': title,
                    'url': url,
                    'page_number': page_num,
                    'status': 'success'
                }
                metadata_file = os.path.join(save_dir, f'metadata_{page_num}.json')
                with open(metadata_file, 'w', encoding='utf-8') as f:
                    json.dump(metadata, f, ensure_ascii=False, indent=2)
                logger.info(f"元数据已保存到: {metadata_file}")

                return {
                    'title': title,
                    'content': content,
                    'status': 'success',
                    'saved_files': {
                        'html': html_file,
                        'screenshot': screenshot_file,
                        'metadata': metadata_file
                    }
                }

            except Exception as e:
                logger.error(f"访问页面时出错: {str(e)}")
                # 保存错误截图
                error_screenshot = os.path.join(save_dir, f'error_screenshot_{page_num}.png')
                try:
                    await page.screenshot(path=error_screenshot)
                    logger.info(f"错误截图已保存到: {error_screenshot}")
                except:
                    pass
                return {
                    'status': 'error',
                    'error': str(e)
                }

            finally:
                await context.close()
                await browser.close()

    except Exception as e:
        logger.error(f"启动浏览器时出错: {str(e)}")
        return {
            'status': 'error',
            'error': str(e)
        }


async def main():
    base_url = "http://shanghai.pbc.gov.cn/fzhshanghai/113577/114832/114909/14678/index{}.html"
    save_dir = "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/pbc/page"
    logger.info("开始爬取任务")

    # 爬取50页
    for page_num in range(1, 51):
        url = base_url.format(page_num)
        logger.info(f"正在爬取第 {page_num} 页: {url}")
        
        try:
            result = await crawl_website(url, save_dir)
            
            if result['status'] == 'success':
                logger.info(f"第 {page_num} 页爬取成功！页面标题: {result['title']}")
                logger.info(f"文件保存位置:")
                for file_type, file_path in result['saved_files'].items():
                    logger.info(f"- {file_type}: {file_path}")
            else:
                logger.error(f"第 {page_num} 页爬取失败: {result.get('error', '未知错误')}")
            
            # 添加延时，避免请求过于频繁
            await asyncio.sleep(2)
            
        except Exception as e:
            logger.error(f"处理第 {page_num} 页时发生错误: {str(e)}")
            continue

    logger.info("所有页面爬取完成")


if __name__ == "__main__":
    asyncio.run(main())