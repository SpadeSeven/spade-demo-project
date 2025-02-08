import json
import logging
import os
import sys
import time
from typing import Optional, Dict, Any

from src.ai.common.llm_models import AIPlatform, SiliconflowLLMModel
from src.ai.common.spade_ai_client import SpadeAiClient

# 配置日志
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(filename)s - %(levelname)s - %(message)s",
)
logger = logging.getLogger(__name__)


def get_weather(location: str, unit: Optional[str] = "celsius") -> Dict[str, Any]:
    """模拟获取天气信息的函数"""
    # 这里只是演示用，实际应该调用真实的天气API
    logger.info(f"获取天气信息: {location}, 单位: {unit}")
    return {"location": location, "temperature": 25, "unit": unit, "condition": "sunny"}


def send_messages(messages):
    time_start = time.time()

    logger.info(f"发送消息: {messages}")
    # 初始化 SpadeAiClient
    client = SpadeAiClient(platform=AIPlatform.SILICONFLOW).ai_client

    # 定义可用的函数，修改为 tools 格式
    functions = [
        {
            "type": "function",  # 添加 type 字段
            "function": {  # 将函数定义包装在 function 字段中
                "name": "get_weather",
                "description": "获取指定位置的天气信息",
                "parameters": {
                    "type": "object",
                    "properties": {
                        "location": {"type": "string", "description": "城市名称"},
                        "unit": {
                            "type": "string",
                            "enum": ["celsius", "fahrenheit"],
                            "description": "温度单位",
                        },
                    },
                    "required": ["location"],
                },
            },
        }
    ]

    response = client.chat.completions.create(
        model=SiliconflowLLMModel.DEEPSEEK_V3,
        messages=messages,
        tools=functions,
        tool_choice="auto",  # 添加 tool_choice 参数
    )
    time_end = time.time()
    logger.info(f"发送消息耗时: {time_end - time_start} 秒")
    return response.choices[0].message


def handle_tool_call(tool_call):
    """处理工具调用"""
    function_name = tool_call.function.name
    arguments = json.loads(tool_call.function.arguments)

    logger.info(f"调用函数: {function_name}, 参数: {arguments}")

    # 函数映射表
    function_map = {
        "get_weather": get_weather,
        # 可以在这里添加更多函数
    }

    if function_name in function_map:
        result = function_map[function_name](**arguments)
        return str(result)
    else:
        raise ValueError(f"未知的函数: {function_name}")


def run_function_calling_demo():
    try:
        messages = [{"role": "user", "content": "How's the weather in Hangzhou?"}]
        message = send_messages(messages)
        logger.info(f"User>\t {messages[0]['content']}")

        if hasattr(message, "tool_calls") and message.tool_calls:
            # 处理所有工具调用
            for tool_call in message.tool_calls:
                messages.append(
                    {"role": "assistant", "content": None, "tool_calls": [tool_call]}
                )

                # 动态处理工具调用
                result = handle_tool_call(tool_call)
                messages.append(
                    {"role": "tool", "tool_call_id": tool_call.id, "content": result}
                )

            message = send_messages(messages)
            logger.info(f"Model>\t {message.content}")
        else:
            logger.info(f"Model>\t {message.content}")

    except Exception as e:
        logger.error(f"发生错误: {str(e)}", exc_info=True)


if __name__ == "__main__":
    # 获取项目根目录路径
    project_root = os.path.abspath(os.path.join(os.path.dirname(__file__), "../../.."))
    print(project_root)
    sys.path.append(project_root)
    run_function_calling_demo()
