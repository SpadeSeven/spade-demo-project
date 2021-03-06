# -*- coding:utf-8 -*-
import logging
import os
import sys
import urllib.parse
import execjs
import json
import time

import argparse

from base64 import b64encode
from lzy.trek.util import _handle_cmd_line
from selenium import webdriver
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile

# 代理
proxy_pool = set()


def _handle_cmd_line(args):
    parser = argparse.ArgumentParser(fromfile_prefix_chars='@', formatter_class=argparse.ArgumentDefaultsHelpFormatter)

    parser.add_argument('--start-index', dest='start_index', action='store', type=int,
                        default='',
                        help='package index, begin from 0')
    parser.add_argument('--end-index', dest='end_index', action='store', type=int,
                        default='',
                        help='package index, begin from 0')
    parser.add_argument('--out-file', dest='out_file', action='store', type=str, default='', help='out file')
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
    with open(os.path.join(workdir, 'js', 'qimai.js'), 'r', encoding='utf-8') as f:
        jsdata = f.read()
    jsdata = execjs.compile(jsdata)
    # http://api.qimai.cn/search/checkHasBundleId?analysis=YVYAQwUDckp8XQFGYgltSD0GY1xwEx9DVVFCWwwYWwxRVQp4UUNyRV5UVAF%2BXCQXAFIBCQcHBwgACFB3G1U%3D&search=cn.wps.moffice
    start = int(options.start_index)
    end = int(options.end_index)
    with open(options.out_file, 'w') as out_file, \
            open(options.out_not_find_file, 'w') as out_not_find_file:
        count_all = 0
        count_find = 0
        count_not_find = 0
        for index in range(start, end):
            appid = str(index)
            try:
                out_file.write(process(appid))
                out_file.write('\n')
                count_find += 1
            except:
                out_not_find_file.write(appid)
                out_not_find_file.write('\n')
                count_not_find += 1
            count_all += 1
            logging.info("procee line: %s, find appid: %s, not find %s" % (count_all, count_find, count_not_find))
    logging.info('over ,exit')


def get_analysis(params, url):
    analysis = jsdata.call('getAnalysis', params, url, time.time())
    return analysis


def process(appid):
    profile = FirefoxProfile()
    fireFoxOptions = webdriver.FirefoxOptions()
    # 静默模式
    fireFoxOptions.add_argument('--headless')
    fireFoxOptions.add_argument('--no-sandbox')
    fireFoxOptions.add_argument('--disable-gpu')
    fireFoxOptions.add_argument('--disable-dev-shm-usage')
    # 第二步：开启“手动设置代理”
    profile.set_preference('network.proxy.type', 1)
    # 第三步：设置代理IP
    # profile.set_preference('network.proxy.http', proxy[0])
    profile.set_preference('network.proxy.http', 'http-dyn.abuyun.com')
    # 第四步：设置代理端口，注意端口是int类型，不是字符串
    # profile.set_preference('network.proxy.http_port', int(proxy[1]))
    profile.set_preference('network.proxy.http_port', 9020)
    # 第五步：设置htpps协议也使用该代理
    profile.set_preference('network.proxy.ssl', 'http-dyn.abuyun.com')
    profile.set_preference('network.proxy.ssl_port', 9020)

    # Proxy auto login
    credentials = '{}:{}'.format('H64W55P7KS0C4Q7D', 'D03F3742C01C78A1')
    credentials = b64encode(bytes(credentials, encoding='utf-8'))
    profile.add_extension('/opt/install/close_proxy_authentication-1.1.xpi')
    # profile.add_extension('D:/download/close_proxy_authentication-1.1.xpi')
    # profile.set_preference('extensions.closeproxyauth.authtoken', credentials)
    profile.set_preference('extensions.closeproxyauth.authtoken', str(credentials, encoding='utf-8'))
    profile.set_preference('modifyheaders.config.active', True)
    profile.set_preference('modifyheaders.headers.count', 1)

    driver = webdriver.Firefox(firefox_profile=profile, firefox_options=fireFoxOptions)

    # base_url = 'view-source:https://api.qimai.cn/andapp/appinfo?analysis=%s&appid=%s'
    base_url = 'view-source:https://api.qimai.cn/andapp/appinfo?analysis=%s&appid=%s'
    analysis = get_analysis(appid, '/andapp/appinfo')
    real_url = base_url % (urllib.parse.quote(analysis), appid)
    try:
        logging.info('get appinfo')
        driver.get(real_url)
        content = driver.find_element_by_xpath('//pre').text
    except Exception:
        logging.error("%s : get appinfo error" % appid)
        raise Exception(appid)
    finally:
        driver.quit()

    result = json.loads(content, encoding='utf-8')
    if result['code'] != 10000:
        logging.error('req code is not 10000, result : %s' % content)
        raise Exception(appid)

    return content
