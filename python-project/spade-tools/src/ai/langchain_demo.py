import os
import sys

import yaml
from langchain.chains import LLMChain
from langchain.prompts import PromptTemplate
from langchain_community.chat_models import ChatOpenAI

from src.utils.logger import LoggerManager

# 将项目根目录添加到 Python 路径
project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
sys.path.append(project_root)


def load_config():
    logger = LoggerManager.get_logger()
    try:
        # 获取配置文件的绝对路径
        config_path = os.path.join(
            os.path.dirname(__file__), "..", "..", "config", "config.yaml"
        )

        with open(config_path, "r", encoding="utf-8") as f:
            config = yaml.safe_load(f)
        logger.info("配置文件加载成功")
        return config
    except FileNotFoundError:
        logger.error("找不到配置文件，请确保 config.yaml 文件存在于正确的位置")
        sys.exit(1)
    except yaml.YAMLError as e:
        logger.error(f"配置文件格式不正确: {e}")
        sys.exit(1)


def create_story_chain(config):
    logger = LoggerManager.get_logger()
    # 创建提示模板
    prompt_template = PromptTemplate(
        input_variables=["topic"],
        template="请用中文写一个关于{topic}的短故事，故事应该包含寓意。",
    )

    try:
        # 从配置文件获取 DeepSeek 配置
        deepseek_config = config["llm"]["deepseek"]

        # 初始化 DeepSeek LLM
        llm = ChatOpenAI(
            openai_api_key=deepseek_config["api_key"],
            openai_api_base=deepseek_config["api_base"],
            model_name=deepseek_config["model_name"],
            temperature=deepseek_config["temperature"],
            max_tokens=deepseek_config.get("max_tokens", 2048),
        )
        logger.info(f"成功初始化 LLM 模型: {deepseek_config['model_name']}")

        # 创建故事生成链
        story_chain = LLMChain(llm=llm, prompt=prompt_template)

        return story_chain
    except KeyError as e:
        logger.error(f"配置文件中缺少必要的配置项: {e}")
        sys.exit(1)
    except Exception as e:
        logger.error(f"创建 LLM 链时发生错误: {e}")
        sys.exit(1)


def main():
    try:
        # 初始化日志系统
        config_path = os.path.join(
            os.path.dirname(__file__), "..", "..", "config", "config.yaml"
        )
        LoggerManager().init_logger(config_path)
        logger = LoggerManager.get_logger()

        logger.info("开始运行故事生成程序")

        # 加载配置
        config = load_config()

        # 创建故事生成链
        story_chain = create_story_chain(config)

        # 获取用户输入
        topic = input("请输入一个故事主题：")
        logger.info(f"用户输入主题: {topic}")

        # 生成故事
        story = story_chain.run(topic=topic)

        logger.info("故事生成成功")
        print("\n生成的故事：")
        print(story)
    except KeyboardInterrupt:
        logger.info("程序被用户中断")
    except Exception as e:
        logger.error(f"程序运行时发生错误: {e}", exc_info=True)


if __name__ == "__main__":
    main()
