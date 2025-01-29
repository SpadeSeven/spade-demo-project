import pandas as pd

# 读取 Excel 文件
excel_file = pd.ExcelFile(
    "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/src/ai/pbc/2025012415370535040.xls"
)

# 获取所有表名
sheet_names = excel_file.sheet_names
print(sheet_names)

# 遍历不同工作表表
# 创建一个空列表，用于存储各个工作表的数据
dfs = []

# 遍历不同工作表表
for sheet_name in sheet_names:
    # 获取当前工作表的数据，设置表头行为 2
    df = excel_file.parse(sheet_name, header=2)

    # 筛选出序号列是数字的行
    df = df[pd.to_numeric(df["序号"], errors="coerce").notnull()]

    # 将序号列转换为整数类型
    df["序号"] = df["序号"].astype(int)

    # 筛选出序号列是顺序整数值的行
    expected_value = 1
    valid_indices = []
    for index, value in df["序号"].items():
        if value == expected_value:
            valid_indices.append(index)
            expected_value += 1
    df = df.loc[valid_indices]

    # 将当前工作表的数据添加到列表中
    dfs.append(df)

# 合并所有工作表的数据
combined_df = pd.concat(dfs, ignore_index=True)

# 将结果保存为 CSV 文件
csv_path = "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/output/pbc/2025012415370535040_filtered_sequence.csv"
combined_df.to_csv(csv_path, index=False)
