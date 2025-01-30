import json
import logging
import os
import time

import pandas as pd

from src.ai.common.llm_models import AIPlatform, SiliconflowLLMModel
from src.ai.common.spade_ai_client import SpadeAiClient

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(filename)s - %(levelname)s - %(message)s",
)
logger = logging.getLogger(__name__)


class UnicornCompany:
    def __init__(self, csv_file):
        """初始化类，读取CSV文件"""
        self.df = pd.read_csv(csv_file)
        # 初始化OpenAI客户端
        self.client = SpadeAiClient(AIPlatform.SILICONFLOW)

    def extract_company_info(self):
        """提取公司相关信息"""
        # 提取所需字段
        companies = []
        for _, row in self.df.iterrows():
            company = {
                "company_name": row["公司名称(中文)"],  # 公司中文名
                "headquarters": row["总部(中文)"],  # 总部所在地
                "industry": row["行业(中文)"],  # 行业
                "founder": row["创始人(中文)"],  # 创始人
            }
            companies.append(company)
        return companies

    def generate_prompt(self, company_info):
        """生成查询工商信息的prompt"""
        prompt = f"""请根据以下公司信息，查询其工商注册信息：
- 公司名称: {company_info['company_name']}
- 总部位置: {company_info['headquarters']}
- 所属行业: {company_info['industry']}
- 创始人: {company_info['founder']}

请以JSON格式返回以下信息：
{{
    "registered_name": "工商注册名称",
    "established_date": "成立时间",
    "registered_address": "注册地址"
}}
"""
        return prompt

    def query_openai(self, prompt):
        """调用OpenAI API获取响应"""
        try:
            model = SiliconflowLLMModel.DEEPSEEK_V2_5
            start_time = time.time()  # 记录开始时间
            logger.info("开始模型查询, 使用模型: " + model)
            response = self.client.ai_client.chat.completions.create(
                model=model,
                messages=[
                    {
                        "role": "system",
                        "content": "你是一个专业的企业信息查询助手，请帮助查询企业工商信息。只返回JSON格式数据，不要包含其他文本。",
                    },
                    {"role": "user", "content": prompt},
                ],
                temperature=1,
                response_format={"type": "json_object"},
            )
            content = response.choices[0].message.content

            # 清理返回的内容
            content = content.strip()
            elapsed_time = time.time() - start_time  # 计算耗时
            logger.info(f"模型查询结束，耗时: {elapsed_time:.2f}秒")

            return content.strip()
        except Exception as e:
            logger.error(f"OpenAI API调用出错: {str(e)}")
            return None

    def process_companies(self, output_file):
        """处理所有公司信息并保存结果"""
        companies = self.extract_company_info()
        results = []

        for i, company in enumerate(companies):
            logger.info(
                f"处理第 {i + 1}/{len(companies)} 个公司: {company['company_name']}"
            )

            prompt = self.generate_prompt(company)
            response = self.query_openai(prompt)

            if response:
                try:
                    # 尝试解析清理后的JSON响应
                    license_info = json.loads(response)
                    result = {
                        "company_name": company["company_name"],
                        "headquarters": company["headquarters"],
                        "industry": company["industry"],
                        "founder": company["founder"],
                        "registered_name": license_info.get("registered_name", ""),
                        "established_date": license_info.get("established_date", ""),
                        "registered_address": license_info.get(
                            "registered_address", ""
                        ),
                    }
                    logger.info(
                        f"查询: {company["company_name"]}, 结果: {result['registered_name']}"
                    )
                    results.append(result)

                    # 每处理10个公司保存一次结果
                    if (i + 1) % 10 == 0:
                        self.save_results_csv(results, output_file)

                except json.JSONDecodeError as e:
                    logger.error(f"JSON解析失败: {response}")
                    logger.error(f"错误信息: {str(e)}")

            # 添加延时避免频繁调用
            time.sleep(1)

        # 最后保存一次结果
        self.save_results_csv(results, output_file)
        return results

    def save_results_csv(self, results, output_file):
        """保存结果到CSV文件"""
        df = pd.DataFrame(results)
        # 设置列的顺序
        columns = [
            "company_name",
            "headquarters",
            "industry",
            "founder",
            "registered_name",
            "established_date",
            "registered_address",
        ]
        # 重命名列
        column_names = {
            "company_name": "公司名称",
            "headquarters": "总部位置",
            "industry": "所属行业",
            "founder": "创始人",
            "registered_name": "工商注册名称",
            "established_date": "成立时间",
            "registered_address": "注册地址",
        }
        df = df[columns].rename(columns=column_names)
        df.to_csv(output_file, index=False, encoding="utf-8-sig")
        logger.info(f"结果已保存到: {output_file}")


def main():
    # 实例化类并处理数据
    unicorn = UnicornCompany("output/rank/hurun/hurun_unicorn_2024.csv")
    output_file = "output/rank/hurun/unicorn/hurun_unicorn_2024_with_license_info.csv"

    # 确保输出目录存在
    os.makedirs(os.path.dirname(output_file), exist_ok=True)

    # 处理公司信息并保存结果
    results = unicorn.process_companies(output_file)

    # 输出处理统计
    logger.info("\n处理完成:")
    logger.info(f"总共处理公司数: {len(results)}")


if __name__ == "__main__":
    main()
