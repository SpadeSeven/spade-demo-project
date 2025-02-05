import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart


def send_email(sender_email, app_password, receiver_email, subject, body):
    """
    使用 Gmail 发送邮件

    参数:
        sender_email: 发件人邮箱（Gmail）
        app_password: Gmail应用专用密码
        receiver_email: 收件人邮箱
        subject: 邮件主题
        body: 邮件正文
    """
    # 创建邮件对象
    message = MIMEMultipart()
    message["From"] = sender_email
    message["To"] = receiver_email
    message["Subject"] = subject

    # 添加邮件正文
    message.attach(MIMEText(body, "plain"))

    try:
        # 创建SMTP会话
        server = smtplib.SMTP("smtp.gmail.com", 587)
        server.starttls()  # 启用TLS加密

        # 登录Gmail
        server.login(sender_email, app_password)

        # 发送邮件
        text = message.as_string()
        server.sendmail(sender_email, receiver_email, text)

        print("邮件发送成功！")

    except Exception as e:
        print(f"发送邮件时出错: {str(e)}")

    finally:
        server.quit()


# 使用示例
if __name__ == "__main__":
    sender_email = "zw939082455@gmail.com"  # 替换为你的Gmail邮箱
    app_password = "gjldiwkefvmqqrnx"  # 替换为你的应用专用密码
    receiver_email = "939082455@qq.com"  # 替换为收件人邮箱

    subject = "测试邮件"
    body = "这是一封测试邮件。"

    send_email(sender_email, app_password, receiver_email, subject, body)
