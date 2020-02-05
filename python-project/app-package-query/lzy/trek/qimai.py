# -*- coding:utf-8 -*-
import logging
import requests
import os
import sys
import urllib.parse
import execjs
import json
import time

import argparse

from fake_useragent import UserAgent
from lzy.trek.util import _handle_cmd_line

# 代理
proxies = {}


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
        proxy = proxies
        for line in input_file:
            fields = line.strip().split(options.input_file_field_separator)
            package_name = fields[options.input_file_package_index]
            try:
                out_file.write(process(options, package_name, proxy))
                out_file.write('\n')
                count_find += 1
            except:
                out_not_find_file.write(line.strip())
                out_not_find_file.write('\n')
                count_not_find += 1
            count_all += 1
            if count_all % 5 == 0:
                get_proxy()
            logging.info("procee line: %s, find package: %s, not find %s" % (count_all, count_find, count_not_find))
    logging.info('over ,exit')


def get_analysis(params, url):
    analysis = jsdata.call('getAnalysis', params, url, time.time())
    return analysis


def process(options, package_name, proxy):
    # 1、计算app_id
    appid = get_appid(package_name, proxy)
    logging.info(appid)
    # 2、查询app_info
    appinfo = get_appinfo(appid, proxy)
    return appinfo


def get_appid(package_name, proxy):
    headers = {
        'Referer': 'https://www.qimai.cn/'
    }
    params = {}
    ua = UserAgent()
    headers['User-Agent'] = ua.chrome
    base_url = 'http://api.qimai.cn/search/checkHasBundleId?analysis=%s&search=%s'
    analysis = get_analysis(package_name, '/search/checkHasBundleId')
    real_url = base_url % (urllib.parse.quote(analysis), package_name)
    params['analysis'] = analysis
    params['search'] = package_name
    res = requests.get(real_url, params=params, headers=headers, proxies=proxy, timeout=60)

    if 200 != res.status_code:
        raise Exception('url : %s cannot find, status_code: %s' % (real_url, res.status_code))
    # package_name
    content = res.content
    # '{"code":10000,"msg":"\u6210\u529f","app_id":"1943968"}'
    result = json.loads(content, encoding='utf-8')
    return result['app_id']


def get_appinfo(appid, proxy):
    headers = {
        'Referer': 'https://www.qimai.cn/'
    }
    params = {}
    ua = UserAgent()
    headers['User-Agent'] = ua.chrome
    base_url = 'https://api.qimai.cn/andapp/appinfo?analysis=%s&appid=%s'
    analysis = get_analysis(appid, '/andapp/appinfo')
    real_url = base_url % (urllib.parse.quote(analysis), appid)
    params['analysis'] = analysis
    params['appid'] = appid
    res = requests.get(real_url, params=params, headers=headers, proxies=proxy, timeout=60)

    if 200 != res.status_code:
        raise Exception('url : %s cannot find, status_code: %s' % (real_url, res.status_code))
    return res.content.decode('utf-8')


def get_proxy():
    global proxies
    PROXY_POOL_URL = 'http://localhost:5555/random'
    try:
        response = requests.get(PROXY_POOL_URL)
        if response.status_code == 200:
            proxies['http'] = response.text
    except ConnectionError:
        return None


def download_img(img_url, out_path, package_name):
    res = requests.get(img_url)
    file = open(os.path.join(out_path, package_name + '.png'), 'wb')
    file.write(res.content)
    file.close()
