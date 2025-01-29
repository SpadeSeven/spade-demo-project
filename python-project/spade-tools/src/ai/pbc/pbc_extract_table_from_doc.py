import pandas as pd
from docx import Document


def extract_table_from_docx(doc_path):
    doc = Document(doc_path)
    tables = []
    for table in doc.tables:
        data = []
        for row in table.rows:
            row_data = []
            for cell in row.cells:
                row_data.append(cell.text)
            data.append(row_data)
        tables.append(pd.DataFrame(data[1:], columns=data[0]))
    return tables


doc_path = "/Users/spade/data/code/my_code/spade-demo-project/python-project/spade-tools/src/ai/pbc/2025012314302295524.doc"
tables = extract_table_from_docx(doc_path)
for table in tables:
    print(table)
