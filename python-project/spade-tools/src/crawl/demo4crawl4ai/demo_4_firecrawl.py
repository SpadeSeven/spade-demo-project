# -*- coding: utf-8 -*-

from firecrawl.firecrawl import FirecrawlApp

app = FirecrawlApp(api_key="fc-YOUR_API_KEY")

# Scrape a website:
scrape_status = app.scrape_url(
    "https://www.crunchbase.com/organization/swiggy",
    params={"formats": ["markdown", "html"]},
)
print(scrape_status)

# Crawl a website:
crawl_status = app.crawl_url(
    "https://www.crunchbase.com/organization/swiggy",
    params={"limit": 100, "scrapeOptions": {"formats": ["markdown", "html"]}},
    poll_interval=30,
)
print(crawl_status)
