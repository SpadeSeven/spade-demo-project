import logging
import os
from logging.handlers import RotatingFileHandler

import yaml


class LoggerManager:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(LoggerManager, cls).__new__(cls)
            cls._instance._initialized = False
        return cls._instance

    def __init__(self):
        if self._initialized:
            return
        self._initialized = True
        self.logger = None

    def init_logger(self, config_path):
        """
        使用配置文件初始化日志系统
        """
        try:
            with open(config_path, "r", encoding="utf-8") as f:
                config = yaml.safe_load(f)

            log_config = config.get("logging", {})

            # 获取项目根目录
            project_root = os.path.abspath(
                os.path.join(os.path.dirname(__file__), "..", "..")
            )

            # 创建日志目录
            log_dir = os.path.join(project_root, "logs")
            os.makedirs(log_dir, exist_ok=True)

            # 配置日志文件路径
            log_file = os.path.join(log_dir, log_config.get("filename", "app.log"))

            # 配置日志格式
            formatter = logging.Formatter(
                log_config.get(
                    "format", "%(asctime)s - %(name)s - %(levelname)s - %(message)s"
                )
            )

            # 配置文件处理器
            file_handler = RotatingFileHandler(
                log_file,
                maxBytes=log_config.get("max_bytes", 1024 * 1024),  # 默认 1MB
                backupCount=log_config.get("backup_count", 5),
                encoding="utf-8",
            )
            file_handler.setFormatter(formatter)

            # 配置控制台处理器
            console_handler = logging.StreamHandler()
            console_handler.setFormatter(formatter)

            # 配置根日志记录器
            root_logger = logging.getLogger()
            root_logger.setLevel(getattr(logging, log_config.get("level", "INFO")))

            # 清除现有的处理器
            root_logger.handlers.clear()

            # 添加处理器
            root_logger.addHandler(file_handler)
            if log_config.get("console_output", True):
                root_logger.addHandler(console_handler)

            self.logger = root_logger

        except Exception as e:
            raise Exception(f"初始化日志系统失败: {str(e)}")

    @classmethod
    def get_logger(cls):
        """
        获取日志记录器实例
        """
        if cls._instance is None or cls._instance.logger is None:
            raise Exception("日志系统未初始化，请先调用 init_logger()")
        return cls._instance.logger
