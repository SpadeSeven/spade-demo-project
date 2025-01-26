import os

import yaml

from src.utils.logger import LoggerManager


def load_config():
    logger = LoggerManager.get_logger()
    try:
        # 获取配置文件的绝对路径
        config_path = os.path.join(os.path.dirname(__file__), '..', '..', 'config', 'config.yaml')

        with open(config_path, 'r', encoding='utf-8') as f:
            config = yaml.safe_load(f)
        logger.info("配置文件加载成功")
        return config
    except FileNotFoundError:
        logger.error("找不到配置文件，请确保 config.yaml 文件存在于正确的位置")
        raise Exception()
    except yaml.YAMLError as e:
        logger.error(f"配置文件格式不正确: {e}")
        raise Exception()


def _load_ai_config():
    pass
