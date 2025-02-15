# -*- coding: utf-8 -*-
from firecrawl import FirecrawlApp

app = FirecrawlApp(api_key="fc-50d835df4b4b4eeba8f7e8b57afcdb78")

# Scrape a website:
scrape_result = app.scrape_url(
    "https://www.crunchbase.com/organization/swiggy", params={"formats": ["html"]}
)
print(scrape_result)
