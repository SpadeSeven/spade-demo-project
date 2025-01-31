import unittest
from pathlib import Path
from company_info import CompanyInfo


class TestCompanyInfo(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        # 读取测试用的HTML文件
        html_path = Path(__file__).parent / "bytedance.html"
        with open(html_path, "r", encoding="utf-8") as f:
            cls.html_content = f.read()
        cls.company = CompanyInfo.from_html(cls.html_content)

    def test_csv_fields(self):
        """测试所有CSV字段的提取"""
        csv_row = self.company.to_csv_row()
        csv_headers = self.company.get_csv_headers()

        # 创建字段值的映射
        field_values = dict(zip(csv_headers, csv_row))

        # 验证每个字段
        self.assertEqual(field_values["公司名称"], "ByteDance")
        self.assertEqual(field_values["网站"], "bytedance.com")
        self.assertEqual(
            field_values["Logo链接"],
            "https://s3-us-west-2.amazonaws.com/cbi-image-service-prd/modified/05eea15a-369a-4366-98ea-96ed489b942d.png?w=3840",
        )
        self.assertEqual(field_values["成立年份"], "2012")
        self.assertEqual(field_values["发展阶段"], "Valuation Change - II | Alive")
        self.assertEqual(field_values["融资总额"], "$7.44B")
        self.assertEqual(
            field_values["总部地址"],
            "No. 23 Yard, North Third Ring West Road Room 222, 2nd Floor, Building 1",
        )
        self.assertEqual(field_values["城市"], "Beijing")
        self.assertEqual(field_values["州/省"], "Beijing")
        self.assertEqual(field_values["邮编"], "100098")
        self.assertEqual(field_values["国家"], "China")
        self.assertEqual(field_values["电话"], "+86 4243510993")
        self.assertEqual(
            field_values["公司描述"],
            "ByteDance is a technology company that provides content platforms in the "
            "social media and entertainment sectors. The company offers products that "
            "helps content creation, discovery, and sharing, including short-form video "
            "platforms, social media services, and enterprise collaboration tools. "
            "ByteDance's products serve a global audience. It was founded in 2012 and "
            "is based in Beijing, China.",
        )
        # self.assertEqual(field_values["专利数量"], "1232")
        # self.assertEqual(
        #     field_values["主要专利领域"],
        #     "video codecs, video compression, videotelephony"
        # )

    def test_csv_headers_match(self):
        """测试CSV表头和数据行长度匹配"""
        csv_row = self.company.to_csv_row()
        csv_headers = self.company.get_csv_headers()
        self.assertEqual(
            len(csv_row),
            len(csv_headers),
            f"CSV行数据长度({len(csv_row)})与表头长度({len(csv_headers)})不匹配",
        )

    # def test_all_fields_have_values(self):
    #     """测试所有字段都有值"""
    #     csv_row = self.company.to_csv_row()
    #     csv_headers = self.company.get_csv_headers()
    #     field_values = dict(zip(csv_headers, csv_row))
    #
    #     for field, value in field_values.items():
    #         self.assertNotEqual(value, "", f"字段 '{field}' 的值为空")
    #
    # def test_dict_output(self):
    #     """测试字典输出格式"""
    #     company_dict = self.company.to_dict()
    #     self.assertEqual(company_dict["name"], "ByteDance")
    #     self.assertEqual(company_dict["founded_year"], 2012)
    #     self.assertEqual(company_dict["headquarters"]["country"], "China")
    #     self.assertEqual(company_dict["patents"]["total_count"], 1232)


if __name__ == "__main__":
    unittest.main()
