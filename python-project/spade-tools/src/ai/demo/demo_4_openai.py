from openai import OpenAI

client = OpenAI(
    api_key="sk-rijqlrrzmnwnfhyahoenktuwcpsspclohjvkifdeaobsmgvq",
    base_url="https://api.siliconflow.cn/v1",
)


def chat_completion():
    response = client.chat.completions.create(
        model="deepseek-ai/DeepSeek-V2.5",
        messages=[{"role": "user", "content": "字节跳动的运营主体公司是哪一家"}],
        stream=True,
    )

    for chunk in response:
        print(chunk.choices[0].delta.content, end="")


def generate_json_data():
    response = client.chat.completions.create(
        model="deepseek-ai/DeepSeek-V2.5",
        messages=[
            {
                "role": "system",
                "content": "You extract email addresses into JSON data.",
            },
            {
                "role": "user",
                "content": "Feeling stuck? Send a message to help@mycompany.com.",
            },
        ],
        response_format={
            "type": "json_schema",
            "json_schema": {
                "name": "email_schema",
                "schema": {
                    "type": "object",
                    "properties": {
                        "email": {
                            "description": "The email address that appears in the input",
                            "type": "string",
                        },
                        "additionalProperties": False,
                    },
                },
            },
        },
    )

    print(response.choices[0].message.content)


# Extract keywords from a block of text.
def extract_keywords():
    response = client.chat.completions.create(
        model="deepseek-ai/DeepSeek-V2.5",
        messages=[
            {
                "role": "system",
                "content": "You will be provided with unstructured data, and your task is to parse it into CSV format.",
            },
            {
                "role": "user",
                "content": "There are many fruits that were found on the recently discovered planet Goocrux. There are neoskizzles that grow there, which are purple and taste like candy. There are also loheckles, which are a grayish blue fruit and are very tart, a little bit like a lemon. Pounits are a bright green color and are more savory than sweet. There are also plenty of loopnovas which are a neon pink flavor and taste like cotton candy. Finally, there are fruits called glowls, which have a very sour and bitter taste which is acidic and caustic, and a pale orange tinge to them.",
            },
        ],
        temperature=1,
        max_tokens=256,
        top_p=1,
    )
    print(response.choices[0].message.content)


if __name__ == "__main__":
    generate_json_data()
