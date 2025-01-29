import asyncio
import logging

from playwright.async_api import async_playwright

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(filename)s - %(levelname)s - %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S",
)
logger = logging.getLogger(__name__)


async def crawl_website(url):
    """
    使用 Playwright 爬取网站
    """
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

                # 获取页面内容
                content = await page.content()
                title = await page.title()

                logger.info(f"成功获取页面内容，标题: {title}")

                # 截图保存（用于调试）
                await page.screenshot(path="screenshot.png")

                return {"title": title, "content": content, "status": "success"}

            except Exception as e:
                logger.error(f"访问页面时出错: {str(e)}")
                # 尝试截图保存错误状态
                try:
                    await page.screenshot(path="error_screenshot.png")
                except Exception as e1:
                    logger.error(f"保存错误截图时出错: {str(e1)}")
                return {"status": "error", "error": str(e)}

            finally:
                await context.close()
                await browser.close()

    except Exception as e:
        logger.error(f"启动浏览器时出错: {str(e)}")
        return {"status": "error", "error": str(e)}


async def main():
    url = (
        "http://shanghai.pbc.gov.cn/fzhshanghai/113577/114832/114909/14678/index1.html"
    )
    logger.info("开始爬取任务")

    result = await crawl_website(url)

    if result["status"] == "success":
        logger.info(f"爬取成功！页面标题: {result['title']}")
        # 这里可以处理爬取到的内容
    else:
        logger.error(f"爬取失败: {result.get('error', '未知错误')}")


if __name__ == "__main__":
    asyncio.run(main())
