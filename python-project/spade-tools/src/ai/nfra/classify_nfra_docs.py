from typing import Dict

import pandas as pd
from openai import OpenAI


def classify_nfra_doc(doc_title: str, doc_content: str) -> Dict[str, str]:
    """
    根据文档标题和内容对NFRA文档进行分类

    Args:
        doc_title: 文档标题
        doc_content: 文档内容

    Returns:
        包含分类信息的字典:
        {
            'doc_type': 文档类型,
            'org_type': 机构类型,
            'position_type': 职位类型(如果是任职资格批复),
            'action_type': 行为类型
        }
    """
    client = OpenAI(
        api_key="sk-rijqlrrzmnwnfhyahoenktuwcpsspclohjvkifdeaobsmgvq",
        base_url="https://api.siliconflow.cn/v1",
    )
    prompt = generate_prompt()
    res = query_openai(prompt, doc_title, doc_content, client)
    print(doc_title)
    print("\n")
    print(res)


def generate_prompt():
    """生成查询工商信息的prompt"""
    prompt = f"""
#### 定位
- 智能助手名称 ：行政许可分类专家
- 主要任务 ：对输入的行政许可文本进行自动分类，识别其所属的许可种类。

#### 能力
- 文本分析 ：能够准确分析行政许可文本的内容和结构。
- 分类识别 ：根据分析结果，将行政许可分类到预定义的种类中。

#### 知识储备
- 新闻种类 ：
  - 银行业金融机构及其分支机构设立、变更、终止以及业务范围审批
  - 银行业金融机构及其分支机构人员任职资格核准
  - 保险业金融机构及其分支机构设立、变更、终止以及业务范围审批
  - 保险业金融机构及其分支机构人员任职资格核准
  - 其他金融机构设立、变更、终止以及业务范围审批
  - 其他金融机构人员任职资格核准
  - 资产管理公司及其分支机构设立、变更、终止以及业务范围审批
  - 资产管理公司及其分支机构人员任职资格核准

#### 使用说明
- 输入 ：行政许可的标题和正文。
- 输出 ：以json格式输出

#### 输出样例
{ {
        "classification": "分类名称",
        "confidence": "0-1置信度",
        "keywords": ["触发分类的关键词列表"]
    } }
"""
    return prompt


def query_openai(prompt, title, content, client):
    """调用OpenAI API获取响应"""
    try:
        response = client.chat.completions.create(
            model="deepseek-ai/DeepSeek-V2.5",
            messages=[
                {
                    "role": "system",
                    "content": prompt,
                },
                {"role": "user", "content": "标题: " + title + "\n 正文: " + content},
            ],
            temperature=1,
            response_format={"type": "json_object"},
        )
        content = response.choices[0].message.content

        # 清理返回的内容
        content = content.strip()

        return content.strip()
    except Exception as e:
        print(f"OpenAI API调用出错: {str(e)}")
        return None


def process_nfra_docs(df: pd.DataFrame) -> pd.DataFrame:
    """
    处理NFRA文档数据框,添加分类信息

    Args:
        df: 包含docTitle和docClob的数据框

    Returns:
        添加了分类列的数据框
    """
    # 添加分类列
    for _, row in df.iterrows():
        classify_nfra_doc(row["docTitle"], row["docClob"])

    return df


def main():
    # 读取CSV文件
    df = pd.read_csv("output/nfra/nfra_details_summary.csv")

    # 处理文档分类
    df = process_nfra_docs(df)


if __name__ == "__main__":
    main()
