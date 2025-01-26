import json
import logging
import os
from datetime import datetime
import pandas as pd
from glob import glob

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

def parse_nfra_list(input_dir: str, output_dir: str) -> None:
    """
    解析金融监管文件列表并保存结果
    
    Args:
        input_dir: 输入JSON文件目录路径
        output_dir: 输出目录路径
    """
    try:
        # 确保输出目录存在
        os.makedirs(output_dir, exist_ok=True)
        
        all_docs = []
        # 获取目录下所有JSON文件
        json_files = glob(os.path.join(input_dir, "*.json"))
        
        if not json_files:
            logger.error(f"在目录 {input_dir} 中未找到JSON文件")
            return
            
        # 读取所有JSON文件
        for json_file in json_files:
            try:
                with open(json_file, 'r', encoding='utf-8') as f:
                    data = json.load(f)
                
                if data['rptCode'] != 200:
                    logger.error(f"文件 {json_file} API返回错误代码: {data['rptCode']}")
                    continue
                    
                # 提取文档列表
                docs = data['data']['rows']
                
                # 清理数据中的换行符
                for doc in docs:
                    if 'docTitle' in doc:
                        doc['docTitle'] = doc['docTitle'].replace('\n', ' ').strip()
                
                all_docs.extend(docs)
                logger.info(f"成功解析文件: {json_file}")
                
            except Exception as e:
                logger.error(f"处理文件 {json_file} 时发生错误: {str(e)}")
                continue
        
        if not all_docs:
            logger.error("没有成功解析任何数据")
            return
            
        # 转换为DataFrame
        df = pd.DataFrame(all_docs)
        
        # 生成输出文件名
        output_file = os.path.join(output_dir, f'nfra_list.csv')
        
        # 保存为CSV文件
        df.to_csv(output_file, index=False, encoding='utf-8')
        logger.info(f"成功合并并保存所有数据到: {output_file}")
        
    except Exception as e:
        logger.error(f"处理过程中发生错误: {str(e)}")

if __name__ == "__main__":
    input_dir = "output/nfra/page"
    output_dir = "output/nfra"
    parse_nfra_list(input_dir, output_dir)