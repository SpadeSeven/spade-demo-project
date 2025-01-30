# -*- coding: utf-8 -*-

from openai import OpenAI

from src.common import load_config


class SpadeAiClient:
    # def __init__(self):
    #     self.ai_platform = AIPlatform.ALIYUN
    #     self.ai_model = AliyunLLMModel.DEEPSEEK_V3
    #     self.__init__(self.ai_platform, self.ai_model)

    def __init__(self, platform, model):
        self.config = load_config.load_config()
        self.ai_platform = platform
        self.ai_model = model
        self.ai_client = self.init_ai_client()

    def init_ai_client(self):
        ai_client = OpenAI(
            api_key=self.config["llm"][self.ai_platform]["api_key"],
            base_url=self.config["llm"][self.ai_platform]["api_base"],
        )
        return ai_client
