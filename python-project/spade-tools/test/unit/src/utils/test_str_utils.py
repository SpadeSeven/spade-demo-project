import unittest

from src.utils import str_utils


class TestStrUtils(unittest.TestCase):
    def test_extract_html_text(self):
        with open("data/1.txt", "r", encoding="utf-8") as f:
            content = f.read()
            print(str_utils.extract_html_text(content))

        with open("data/2.html", "r", encoding="utf-8") as f:
            content = f.read()
            print(str_utils.extract_html_text(content))


if __name__ == "__main__":
    unittest.main()
