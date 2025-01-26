import unittest
from unittest.mock import patch, MagicMock
from src.ai.zjh.extract_license_info import extract_company_name

class TestExtractLicenseInfo(unittest.TestCase):
    def setUp(self):
        """
        测试前的设置
        """
        # 模拟配置
        self.mock_config = {
            'model': {
                'temperature': 0,
                'model_name': 'gpt-3.5-turbo',
                'max_tokens': 256,
                'api_key': 'test-key'
            }
        }
        
    @patch('src.ai.zjh.extract_license_info.load_config')
    @patch('src.ai.zjh.extract_license_info.OpenAI')
    def test_extract_company_name_success(self, mock_openai, mock_load_config):
        """
        测试成功提取公司名称的情况
        """
        # 设置模拟返回值
        mock_load_config.return_value = self.mock_config
        mock_llm = MagicMock()
        mock_llm.predict.return_value = "测试科技有限公司"
        mock_openai.return_value = mock_llm
        
        # 测试用例
        test_cases = [
            {
                "title": "关于同意测试科技有限公司开展业务的批复",
                "expected": "测试科技有限公司"
            },
        ]
        
        for case in test_cases:
            result = extract_company_name(case["title"])
            self.assertEqual(result, case["expected"])
            
    @patch('src.ai.zjh.extract_license_info.load_config')
    @patch('src.ai.zjh.extract_license_info.OpenAI')
    def test_extract_company_name_empty(self, mock_openai, mock_load_config):
        """
        测试未找到公司名称的情况
        """
        # 设置模拟返回值
        mock_load_config.return_value = self.mock_config
        mock_llm = MagicMock()
        mock_llm.predict.return_value = ""
        mock_openai.return_value = mock_llm
        
        result = extract_company_name("无关文本")
        self.assertEqual(result, "")
        
    @patch('src.ai.zjh.extract_license_info.load_config')
    @patch('src.ai.zjh.extract_license_info.OpenAI')
    def test_extract_company_name_error(self, mock_openai, mock_load_config):
        """
        测试发生错误时的情况
        """
        # 设置模拟返回值
        mock_load_config.return_value = self.mock_config
        mock_llm = MagicMock()
        mock_llm.predict.side_effect = Exception("API错误")
        mock_openai.return_value = mock_llm
        
        result = extract_company_name("测试文本")
        self.assertEqual(result, "")

if __name__ == '__main__':
    unittest.main()
