from dataclasses import dataclass, asdict
from typing import List, Dict, Optional

from lxml import html


@dataclass
class CompanyInfo:
    name: str = ""
    website: str = ""
    logo_url: str = ""
    founded_year: Optional[int] = None
    stage: str = ""
    total_raised: str = ""
    headquarters: Dict[str, str] = None
    description: str = ""
    patents: Dict[str, any] = None

    def __post_init__(self):
        if self.headquarters is None:
            self.headquarters = {
                "street": "",
                "city": "",
                "state": "",
                "zip": "",
                "country": "",
                "phone": "",
            }
        if self.patents is None:
            self.patents = {"total_count": 0, "top_topics": []}

    @classmethod
    def get_csv_headers(cls) -> List[str]:
        """获取CSV表头"""
        return [
            "公司名称",
            "网站",
            "Logo链接",
            "成立年份",
            "发展阶段",
            "融资总额",
            "总部地址",
            "城市",
            "州/省",
            "邮编",
            "国家",
            "电话",
            "公司描述",
            "专利数量",
            "主要专利领域",
        ]

    def to_csv_row(self) -> List[str]:
        """转换为CSV行数据"""
        return [
            self.name,
            self.website,
            self.logo_url,
            str(self.founded_year) if self.founded_year else "",
            self.stage,
            self.total_raised,
            self.headquarters["street"],
            self.headquarters["city"],
            self.headquarters["state"],
            self.headquarters["zip"],
            self.headquarters["country"],
            self.headquarters["phone"],
            self.description,
            str(self.patents["total_count"]),
            ", ".join(self.patents["top_topics"]),
        ]

    def to_dict(self) -> Dict:
        """转换为字典格式"""
        return asdict(self)

    # @classmethod
    # def save_to_csv(cls, companies: List['CompanyInfo'], filename: str):
    #     """保存公司列表到CSV文件"""
    #     with open(filename, 'w', newline='', encoding='utf-8') as f:
    #         writer = csv.writer(f)
    #         writer.writerow(cls.get_csv_headers())
    #         for company in companies:
    #             writer.writerow(company.to_csv_row())

    @classmethod
    def from_html(cls, html_content: str) -> "CompanyInfo":
        """从HTML解析公司信息"""
        tree = html.fromstring(html_content)
        company = cls()

        # 解析公司名称
        name = tree.xpath(
            '//h1[@class="cbi-default pr-2 text-2xl font-medium text-black"]/text()'
        )
        if name:
            company.name = name[0].strip()

        # 解析网站
        website = tree.xpath(
            '//a[@class="color--blue padding--top--s text-sm font-medium"]/text()'
        )
        if website:
            company.website = website[0].strip()

        # 解析logo URL
        logo = tree.xpath(
            "//img[contains(@alt, 'company logo')and contains(@class, 'my-auto')]/@src"
        )
        if logo:
            company.logo_url = logo[0]

        # 解析基本信息
        kpi_items = tree.xpath('//div[@class="Kpi_kpiItem__2CwXD"]')
        for item in kpi_items:
            title = item.xpath(".//h2/text()")
            value = item.xpath(
                './/span[@class="break-words font-cbi-default text-base font-medium text-black"]/text()'
            )

            if not (title and value):
                continue

            title_text = title[0].strip()
            value_text = "".join(value)

            if "Founded Year" in title_text:
                try:
                    company.founded_year = int(value_text)
                except ValueError:
                    pass
            elif "Stage" in title_text:
                company.stage = value_text
            elif "Total Raised" in title_text:
                company.total_raised = value_text

        # 解析公司描述
        desc = tree.xpath('//p[@data-test="description"]/text()')
        if desc:
            company.description = desc[0].strip()

        # 解析总部信息
        address = tree.xpath('//address[@data-test="address"]')
        if address:
            # 街道地址
            street = address[0].xpath('.//p[@data-test="street"]/text()')
            if street:
                company.headquarters["street"] = street[0].strip()

            # 城市、州、邮编
            city_state = address[0].xpath('.//p[@data-test="city-state-zip"]/text()')
            if city_state:
                # parts = city_state[0].strip().split(', ')
                if len(city_state) >= 3:
                    company.headquarters["city"] = city_state[0].strip().split(",")[0]
                    company.headquarters["state"] = city_state[1].strip().split(",")[0]
                    company.headquarters["zip"] = city_state[2].strip().split(",")[0]

            # 国家
            country = address[0].xpath('.//p[@data-test="country"]/text()')
            if country:
                company.headquarters["country"] = country[0].strip()

            # 电话
            phone = address[0].xpath('.//p[@data-test="phone"]/text()')
            if phone:
                company.headquarters["phone"] = phone[0].strip()

        # 解析专利信息
        # patents_section = tree.xpath('//div[@data-test="patents-section"]')
        # if patents_section:
        #     # 专利总数
        #     patent_count = patents_section[0].xpath('.//p[@class="cbi-default flex-1 text-base text-black"]/text()')
        #     if patent_count:
        #         count_match = re.search(r'(\d+)', patent_count[0])
        #         if count_match:
        #             company.patents['total_count'] = int(count_match.group(1))
        #
        #     # 主要专利领域
        #     topics = patents_section[0].xpath('.//li[@class="margin--left--xl"]/text()')
        #     company.patents['top_topics'] = [topic.strip() for topic in topics]

        return company
