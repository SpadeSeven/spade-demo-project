from langchain_core.messages import HumanMessage, SystemMessage
from langchain_openai import ChatOpenAI

model = ChatOpenAI(
    model="qwen-max-2025-01-25",
    openai_api_base="https://dashscope.aliyuncs.com/compatible-mode/v1",
    openai_api_key="sk-fa5986a1422c491fbeae3cdf2768811b",
)
messages = [
    SystemMessage("Translate the following from English into chinese"),
    HumanMessage("hi!"),
]

print(model.invoke(messages))
