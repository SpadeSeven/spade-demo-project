# -*- coding: utf-8 -*-

from openai import OpenAI

from src.common import load_config


class SpadeAiClient:
    def __init__(self, platform):
        self.config = load_config.load_config()
        self.ai_platform = platform
        self.ai_client = self.init_ai_client()

    def init_ai_client(self):
        ai_client = OpenAI(
            api_key=self.config["llm"][self.ai_platform]["api_key"],
            base_url=self.config["llm"][self.ai_platform]["api_base"],
        )
        return ai_client
