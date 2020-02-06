# -*- coding:utf-8 -*-
import logging
import requests
import os
import sys
import urllib.parse
import execjs
import json
import random
import telnetlib
import time

import argparse

from lzy.trek.util import _handle_cmd_line
from selenium import webdriver

# 代理
proxy_pool = []


def _handle_cmd_line(args):
    parser = argparse.ArgumentParser(fromfile_prefix_chars='@', formatter_class=argparse.ArgumentDefaultsHelpFormatter)

    parser.add_argument('--input-file', dest='input_file', action='store', type=str, default='', help='input file')
    parser.add_argument('--input-file-field-separator', dest='input_file_field_separator', action='store', type=str,
                        default='', help='input file field separator')
    parser.add_argument('--input-file-package-index', dest='input_file_package_index', action='store', type=int,
                        default='',
                        help='package index, begin from 0')
    parser.add_argument('--out-file', dest='out_file', action='store', type=str, default='', help='out file')
    parser.add_argument('--out-icon-path', dest='out_icon_path', action='store', type=str, default='',
                        help='out icon path')
    parser.add_argument('--out-not-find-file', dest='out_not_find_file', action='store', type=str, default='',
                        help='out not find file')
    parser.add_argument('--log-name', dest='log_name', action='store', type=str, default='',
                        help='log name')

    options = parser.parse_args(args)
    return options


# 七麦
jsdata = ''


def main(workdir):
    options = _handle_cmd_line(sys.argv[1:])

    logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
                        filename=os.path.join(workdir, 'logs', options.log_name))

    # compile js
    global jsdata
    get_proxy()
    with open(os.path.join(workdir, 'js', 'qimai.js'), 'r', encoding='utf-8') as f:
        jsdata = f.read()
    jsdata = execjs.compile(jsdata)
    # http://api.qimai.cn/search/checkHasBundleId?analysis=YVYAQwUDckp8XQFGYgltSD0GY1xwEx9DVVFCWwwYWwxRVQp4UUNyRV5UVAF%2BXCQXAFIBCQcHBwgACFB3G1U%3D&search=cn.wps.moffice
    with open(options.input_file, 'r') as input_file, \
            open(options.out_file, 'w') as out_file, \
            open(options.out_not_find_file, 'w') as out_not_find_file:
        count_all = 0
        count_find = 0
        count_not_find = 0
        for line in input_file:
            if len(proxy_pool) < 1:
                get_proxy()
            fields = line.strip().split(options.input_file_field_separator)
            package_name = fields[options.input_file_package_index]
            try:
                out_file.write(process(package_name))
                out_file.write('\n')
                count_find += 1
            except:
                out_not_find_file.write(line.strip())
                out_not_find_file.write('\n')
                count_not_find += 1
            count_all += 1
            if count_all % len(proxy_pool) == 0:
                get_proxy()
            logging.info("procee line: %s, find package: %s, not find %s" % (count_all, count_find, count_not_find))
    logging.info('over ,exit')


def get_analysis(params, url):
    analysis = jsdata.call('getAnalysis', params, url, time.time())
    return analysis


def process(package_name):
    app_id_base_url = 'http://api.qimai.cn/search/checkHasBundleId?analysis=%s&search=%s'
    analysis = get_analysis(package_name, '/search/checkHasBundleId')
    app_id_real_url = app_id_base_url % (urllib.parse.quote(analysis), package_name)

    # driver = webdriver.Firefox()
    proxy = proxy_pool[random.randint(0, len(proxy_pool) - 1)]
    chromeOptions = webdriver.ChromeOptions()
    # 静默模式
    chromeOptions.add_argument('headless')
    chromeOptions.add_argument('--no-sandbox')
    chromeOptions.add_argument('--disable-gpu')
    chromeOptions.add_argument('--disable-dev-shm-usage')
    chromeOptions.add_argument('--proxy-server=%s' % proxy)
    driver = webdriver.Chrome(chrome_options=chromeOptions)
    try:
        driver.get(app_id_real_url)
        content = driver.find_element_by_xpath('//pre').text
        result = json.loads(content, encoding='utf-8')
        appid = result['app_id']
    except Exception:
        driver.close()
        raise Exception(package_name)

    logging.info(package_name + ' -> ' + appid)

    # base_url = 'view-source:https://api.qimai.cn/andapp/appinfo?analysis=%s&appid=%s'
    base_url = 'https://api.qimai.cn/andapp/appinfo?analysis=%s&appid=%s'
    analysis = get_analysis(appid, '/andapp/appinfo')
    real_url = base_url % (urllib.parse.quote(analysis), appid)
    try:
        driver.get(real_url)
        content = driver.find_element_by_xpath('//pre').text
    except Exception:
        raise Exception(package_name)
    finally:
        driver.close()
    return content


def get_proxy():
    logging.info('get proxt')
    global proxy_pool
    proxy_pool = []
    PROXY_POOL_URL = 'http://api3.xiguadaili.com/ip/?tid=557590085464460&num=100&format=json&protocol=http'
    try:
        response = requests.get(PROXY_POOL_URL)
        if response.status_code == 200:
            req = response.text
            proxies = json.loads(req)
            for proxy in proxies:
                if valid_proxy(proxy['host'], proxy['port']):
                    proxy_url = 'http://%s:%s' % (proxy['host'], proxy['port'])
                    proxy_pool.append(proxy_url)
    except ConnectionError:
        return None


def valid_proxy(host, port):
    try:
        telnetlib.Telnet(host=host, port=int(port), timeout=2)
    except Exception:
        return False
    return True
