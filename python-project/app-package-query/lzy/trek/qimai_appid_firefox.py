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
    proxy = get_one_proxy()
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
    profile.set_preference('network.proxy.ssl', proxy[0])
    profile.set_preference('network.proxy.ssl_port', int(proxy[1]))
    profile.set_preference("network.proxy.username", 'H1DI80S0PF90390D')
    profile.set_preference("network.proxy.password", 'F0298CB7E5CD3207')
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


def get_proxy():
    logging.info('get proxy')
    global proxy_pool
    proxy_pool = set()
    PROXY_POOL_URL = 'http://api3.xiguadaili.com/ip/?tid=555389857434076&num=100&format=json&protocol=https&longlife=20&category=2&delay=5'
    try:
        response = requests.get(PROXY_POOL_URL)
        if response.status_code == 200:
            req = response.text
            proxies = json.loads(req)
            for proxy in proxies:
                proxy_pool.add((proxy['host'], proxy['port']))
    except ConnectionError:
        return None


def get_one_proxy():
    logging.info('get one proxy')
    if len(proxy_pool) > 0:
        proxy_tuple = proxy_pool.pop()
        if valid_proxy(host=proxy_tuple[0], port=proxy_tuple[1]):
            return (proxy_tuple[0], proxy_tuple[1])
        else:
            return get_one_proxy()
    else:
        get_zhima_proxy()
        return get_one_proxy()


def valid_proxy(host, port):
    logging.info('valid proxy')
    try:
        telnetlib.Telnet(host=host, port=int(port), timeout=2)
    except Exception:
        return False
    return True


def get_zhima_proxy():
    logging.info('get proxy')
    global proxy_pool
    proxy_pool = set()
    PROXY_POOL_URL = 'http://http.tiqu.alicdns.com/getip3?num=1&type=2&pro=&city=0&yys=0&port=1&pack=83085&ts=0&ys=0&cs=0&lb=1&sb=0&pb=45&mr=1&regions=&gm=4'
    try:
        response = requests.get(PROXY_POOL_URL)
        if response.status_code == 200:
            req = response.text
            proxies = json.loads(req)
            for proxy in proxies['data']:
                proxy_pool.add((proxy['ip'], proxy['port']))
    except ConnectionError:
        return None
