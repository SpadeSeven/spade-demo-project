import asyncio
import json
import logging
import os
from urllib.parse import urljoin

import pandas as pd
from playwright.async_api import async_playwright

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(filename)s - %(levelname)s - %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S",
)
logger = logging.getLogger(__name__)


async def download_attachment(page, save_dir, index):
    """
    下载页面中的附件
    """
    attachments_dir = os.path.join(save_dir, "attachments", f"page_{index}")
    os.makedirs(attachments_dir, exist_ok=True)

    # 查找所有文档链接
    doc_links = await page.query_selector_all(
        'a[href*=".doc"], a[href*=".docx"], a[href*=".pdf"], a[href*=".xls"], a[href*=".xlsx"]'
    )

    downloaded_files = []
    for link in doc_links:
        try:
            href = await link.get_attribute("href")
            if not href:
                continue

            # 获取完整URL
            full_url = urljoin(page.url, href)

            # 获取文件名
            filename = os.path.basename(href)
            if not filename:
                filename = (
                    f"document_{len(downloaded_files) + 1}{os.path.splitext(href)[1]}"
                )

            # 下载文件
            file_path = os.path.join(attachments_dir, filename)

            logger.info(f"正在下载附件: {full_url}")

            # 使用 page.goto 访问文件链接并等待下载
            async with page.expect_download() as download_info:
                await link.click()
            download = await download_info.value

            # 保存文件
            await download.save_as(file_path)

            downloaded_files.append(
                {
                    "original_url": full_url,
                    "saved_path": file_path,
                    "filename": filename,
                }
            )

            logger.info(f"附件已保存到: {file_path}")

        except Exception as e:
            logger.error(f"下载附件时出错: {str(e)}")
            continue

    return downloaded_files


async def crawl_website(url, save_dir, index):
    """
    使用 Playwright 爬取网站并保存结果
    """
    # 确保保存目录存在
    os.makedirs(save_dir, exist_ok=True)

    try:
        async with async_playwright() as p:
            # 启动浏览器，设置更长的超时时间和其他选项
            browser = await p.chromium.launch(
                headless=True,
                args=["--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage"],
            )

            # 创建上下文并设置超时
            context = await browser.new_context(
                viewport={"width": 1920, "height": 1080},
                user_agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
            )

            # 创建新页面
            page = await context.new_page()

            # 设置更长的超时时间（2分钟）
            page.set_default_timeout(120000)

            logger.info(f"开始访问网站: {url}")

            # 访问网站
            try:
                response = await page.goto(url, wait_until="networkidle")
                if response:
                    logger.info(f"页面加载状态码: {response.status}")

                # 等待页面加载完成
                await page.wait_for_load_state("domcontentloaded")

                # 下载附件
                downloaded_files = await download_attachment(page, save_dir, index)

                # 获取页面内容
                content = await page.content()
                title = await page.title()

                logger.info(f"成功获取页面内容，标题: {title}")

                # 使用 index 命名文件
                html_file = os.path.join(save_dir, f"page_{index}.html")
                with open(html_file, "w", encoding="utf-8") as f:
                    f.write(content)
                logger.info(f"HTML内容已保存到: {html_file}")

                # 保存截图
                screenshot_file = os.path.join(save_dir, f"screenshot_{index}.png")
                await page.screenshot(path=screenshot_file)
                logger.info(f"截图已保存到: {screenshot_file}")

                # 保存元数据
                metadata = {
                    "title": title,
                    "url": url,
                    "index": index,
                    "status": "success",
                    "attachments": downloaded_files,
                }
                metadata_file = os.path.join(save_dir, f"metadata_{index}.json")
                with open(metadata_file, "w", encoding="utf-8") as f:
                    json.dump(metadata, f, ensure_ascii=False, indent=2)
                logger.info(f"元数据已保存到: {metadata_file}")

                return {
                    "title": title,
                    "content": content,
                    "status": "success",
                    "saved_files": {
                        "html": html_file,
                        "screenshot": screenshot_file,
                        "metadata": metadata_file,
                    },
                }

            except Exception as e:
                logger.error(f"访问页面时出错: {str(e)}")
                # 保存错误截图
                error_screenshot = os.path.join(
                    save_dir, f"error_screenshot_{index}.png"
                )
                try:
                    await page.screenshot(path=error_screenshot)
                    logger.info(f"错误截图已保存到: {error_screenshot}")
                except Exception as e1:
                    logger.error("保存错误截图时出错: ", e1)
                return {"status": "error", "error": str(e)}

            finally:
                await context.close()
                await browser.close()

    except Exception as e:
        logger.error(f"启动浏览器时出错: {str(e)}")
        return {"status": "error", "error": str(e)}


async def main():
    """
    主函数：从CSV文件读取URL并爬取
    """
    save_dir = "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/pbc/detail"
    csv_path = "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/pbc/pbc_policies.csv"

    logger.info("开始爬取任务")

    # 读取CSV文件
    df = pd.read_csv(csv_path)

    # 遍历每个URL
    for index, row in df.iterrows():
        url = row["url"]
        logger.info(f"正在爬取第 {index + 1} 条记录: {url}")

        try:
            result = await crawl_website(url, save_dir, index)

            if result["status"] == "success":
                logger.info(
                    f"第 {index + 1} 条记录爬取成功！页面标题: {result['title']}"
                )
                logger.info(f"文件保存位置:{save_dir}")
                for file_type, file_path in result["saved_files"].items():
                    logger.info(f"- {file_type}: {file_path}")
            else:
                logger.error(
                    f"第 {index + 1} 条记录爬取失败: {result.get('error', '未知错误')}"
                )

            # 添加延时，避免请求过于频繁
            await asyncio.sleep(2)

        except Exception as e:
            logger.error(f"处理第 {index + 1} 条记录时发生错误: {str(e)}")
            continue

    logger.info("所有页面爬取完成")


if __name__ == "__main__":
    asyncio.run(main())
