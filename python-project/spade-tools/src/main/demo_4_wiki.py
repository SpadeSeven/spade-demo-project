# -*- coding: utf-8 -*-

# 添加用于查询公司信息的函数
def get_company_info(company_name):
    import wikipedia

    # 设置简体中文
    wikipedia.set_lang("zh")
    summary = wikipedia.summary(company_name)
    print(summary)

    page = wikipedia.page(company_name)
    for link in page.links:
        print(link)


if __name__ == "__main__":
    company = "华为技术有限公司"
    get_company_info(company)
