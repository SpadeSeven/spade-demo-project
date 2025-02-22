# -*- coding: utf-8 -*-
from firecrawl import FirecrawlApp

app = FirecrawlApp(api_url="http://localhost:3002")

# Scrape a website:
scrape_result = app.scrape_url(
    "https://www.montnets.com/", params={"formats": ["html"]}
)
print(scrape_result)
