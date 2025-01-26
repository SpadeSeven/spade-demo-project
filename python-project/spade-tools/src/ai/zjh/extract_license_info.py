import json
import csv
import os
import sys
import re
import logging
from html import unescape
from bs4 import BeautifulSoup
import glob
from langchain.chains import LLMChain
from langchain.prompts import PromptTemplate
from langchain_community.chat_models import ChatOpenAI
from src.utils.logger import LoggerManager


# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler('logs/license_extract.log', encoding='utf-8'),
        logging.StreamHandler()
    ]
)
logger = logging.getLogger(__name__)

# 将项目根目录添加到 Python 路径
project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', '..', '..'))
logger.info(f"项目根目录: {project_root}")
sys.path.append(project_root)

from src.common import load_config

# 全局变量存储 LLM 实例
_llm = None

def init_llm():
    """
    初始化 LLM 模型
    
    Returns:
        ChatOpenAI: 初始化好的 LLM 模型实例
    """
    global _llm
    if _llm is None:
        try:
            # 加载配置
            config = load_config.load_config()
            # 从配置文件获取 DeepSeek 配置
            deepseek_config = config['llm']['deepseek']

            # 初始化 DeepSeek LLM
            _llm = ChatOpenAI(
                openai_api_key=deepseek_config['api_key'],
                openai_api_base=deepseek_config['api_base'],
                model_name=deepseek_config['model_name'],
                temperature=deepseek_config['temperature'],
                max_tokens=deepseek_config.get('max_tokens', 2048)
            )
            logger.info(f"成功初始化 LLM 模型: {deepseek_config['model_name']}")
        except Exception as e:
            logger.error(f"初始化 LLM 模型失败: {str(e)}")
            raise
    return _llm

def extract_company_name(title):
    """
    使用 LangChain 从标题中提取行政许可对象（公司名称）
    
    Args:
        title: 标题文本
        
    Returns:
        str: 提取出的公司名称，如果没有找到则返回空字符串
    """
    try:
        # 获取 LLM 实例
        llm = init_llm()
        
        # 创建提示模板
        prompt_template = PromptTemplate(
            input_variables=["title"],
            template="""
            从以下行政许可标题中提取公司名称：
            {title}
            
            只需返回完整的公司名称，如果没有找到则返回空字符串。不要包含其他解释。
            """
        )
        
        # 生成提示
        prompt = prompt_template.format(title=title)
        # 获取结果
        company_name = llm.predict(prompt).strip()
        return company_name
    except Exception as e:
        logger.error(f"提取公司名称时出错: {str(e)}")
        return ''

def clean_html_text(html_content):
    """清理HTML内容，返回纯文本"""
    if not html_content:
        return ''
    
    # 使用BeautifulSoup解析HTML
    soup = BeautifulSoup(html_content, 'html.parser')
    
    # 获取纯文本内容
    text = soup.get_text(separator=' ', strip=True)
    
    # HTML实体解码
    text = unescape(text)
    
    # 清理特殊字符
    text = re.sub(r'\s+', ' ', text)  # 合并多个空白字符
    text = re.sub(r'&ensp;', ' ', text)  # 处理特殊空格
    text = text.strip()
    
    return text

def extract_license_info(input_dir, output_file):
    """
    从指定目录读取所有JSON文件并合并处理
    
    Args:
        input_dir: JSON文件所在目录
        output_file: 输出CSV文件路径
    """
    # 创建output目录(如果不存在)
    os.makedirs(os.path.dirname(output_file), exist_ok=True)
    
    # 获取目录下所有JSON文件
    json_files = glob.glob(os.path.join(input_dir, '*.json'))
    if not json_files:
        logger.warning(f"在 {input_dir} 目录下未找到JSON文件")
        return
        
    # 准备CSV表头 - publicInfo字段
    public_info_fields = [
        '发文日期', '公开方式', '索引号', '公开范围', '分类', 
        '主题词', '分类2', '文件编号', '主站发布机构', 
        '文号', '发文单位', '发布机构', '公开时限'
    ]
    
    # basic字段
    basic_fields = [
        '部门', '来源', '派出机构名称', '主站来源', 
        '职位', '作者'
    ]
    
    # 其他字段 - 添加行政许可对象字段
    other_fields = [
        '发布时间', '标题', '行政许可对象', '副标题', '内容', '原文链接',
        'channelName', 'channelId', 'manuscriptId'
    ]
    
    headers = public_info_fields + basic_fields + other_fields
    
    # 首先写入表头
    with open(output_file, 'w', encoding='utf-8-sig', newline='') as f:
        writer = csv.writer(f)
        writer.writerow(headers)
    
    # 使用追加模式处理每个文件的数据
    with open(output_file, 'a', encoding='utf-8-sig', newline='') as f:
        writer = csv.writer(f)
        
        # 处理每个JSON文件
        for json_file in sorted(json_files):
            try:
                logger.info(f"正在处理文件: {json_file}")
                with open(json_file, 'r', encoding='utf-8') as jf:
                    data = json.load(jf)
                
                # 提取结果列表
                results = data['data']['results']
                
                for item in results:
                    # 初始化所有字段为空字符串
                    row_data = {field: '' for field in headers}
                    
                    # 从domainMetaList中提取信息
                    for meta in item['domainMetaList']:
                        if meta['domainMetadataCode'] == 'publicInfo':
                            for result in meta['resultList']:
                                field_map = {
                                    'fwrq': '发文日期',
                                    'gkfs': '公开方式',
                                    'syh': '索引号',
                                    'gkfw': '公开范围',
                                    'fl': '分类',
                                    'ztc': '主题词',
                                    'cesysj': '分类2',
                                    'wjbh': '文件编号',
                                    'zzfbjg': '主站发布机构',
                                    'wh': '文号',
                                    'fwdw': '发文单位',
                                    'fbjg': '发布机构',
                                    'gksx': '公开时限'
                                }
                                if result['key'] in field_map:
                                    row_data[field_map[result['key']]] = clean_html_text(result['value'])
                            
                        elif meta['domainMetadataCode'] == 'basic':
                            for result in meta['resultList']:
                                field_map = {
                                    'section': '部门',
                                    'infosource': '来源',
                                    'pcjgs': '派出机构名称',
                                    'homesource': '主站来源',
                                    'zw': '职位',
                                    'authors': '作者'
                                }
                                if result['key'] in field_map:
                                    row_data[field_map[result['key']]] = clean_html_text(result['value'])
                    
                    # 提取其他字段
                    row_data['发布时间'] = item.get('publishedTimeStr', '')
                    title = clean_html_text(item['title'])
                    row_data['标题'] = title
                    # 提取行政许可对象
                    row_data['行政许可对象'] = extract_company_name(title)
                    row_data['副标题'] = clean_html_text(item.get('subTitle', ''))
                    row_data['内容'] = clean_html_text(item['content'])
                    row_data['原文链接'] = item.get('url', '')
                    row_data['channelName'] = item.get('channelName', '')
                    row_data['channelId'] = item.get('channelId', '')
                    row_data['manuscriptId'] = item.get('manuscriptId', '')
                    
                    # 按headers顺序写入数据
                    writer.writerow([row_data[field] for field in headers])
            except Exception as e:
                logger.error(f"处理文件 {json_file} 时出错: {str(e)}", exc_info=True)
                continue

if __name__ == '__main__':
    # 初始化日志系统
    config_path = os.path.join(os.path.dirname(__file__), '..', '..','..', 'config', 'config.yaml')
    LoggerManager().init_logger(config_path)
    logger = LoggerManager.get_logger()
    input_dir = 'output/zjh'  # 证监会数据目录
    output_file = 'output/zjh_license_info.csv'
    extract_license_info(input_dir, output_file)
    logger.info(f'已将证监会行政许可信息保存至: {output_file}') 