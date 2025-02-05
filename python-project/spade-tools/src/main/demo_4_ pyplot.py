import matplotlib.pyplot as plt
import pandas as pd

# 读取数据
data = pd.read_csv("~/Downloads/日期高度票.txt", sep="\t")

# 将日期列转换为日期时间类型
data["日期"] = pd.to_datetime(data["日期"], format="%Y%m%d")

# 绘制折线图
plt.plot(data["日期"], data["高度"])

# 设置图表标题和坐标轴标签
plt.title("高度随时间变化")
plt.xlabel("日期")
plt.ylabel("高度")

# 自动旋转 x 轴标签以避免重叠
plt.xticks(rotation=45)

# 显示图表
plt.show()
