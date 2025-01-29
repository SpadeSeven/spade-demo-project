# 输入一个html content
# 提取html文本内容，剔除格式，保留html的换行

from bs4 import BeautifulSoup


def extract_html_text(html_content: str) -> str:
    """
    从HTML内容中提取纯文本，保留换行符，只提取body内容

    Args:
        html_content (str): HTML字符串内容

    Returns:
        str: 提取出的纯文本内容
    """
    if not html_content:
        return ""

    # 使用BeautifulSoup解析HTML
    soup = BeautifulSoup(html_content, "html.parser")

    # 移除不需要的标签
    for tag in soup.find_all(["head", "script", "style"]):
        tag.decompose()

    # 替换<br>和</p>标签为换行符
    for br in soup.find_all(["br", "p"]):
        br.replace_with("\n" + br.text)

    # 获取文本内容
    text = soup.get_text()

    # 清理多余的空白字符
    text = "\n".join(line.strip() for line in text.splitlines() if line.strip())

    return text
