# -*- coding:utf-8 -*-
import argparse
import logging
import os
import sys
import urllib.parse
import execjs
import json
import string
import time
import zipfile

from lzy.trek.util import _handle_cmd_line
from selenium import webdriver


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

proxy_auth_plugin_path = ''


def main(workdir):
    options = _handle_cmd_line(sys.argv[1:])

    logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
                        filename=os.path.join(workdir, 'logs', options.log_name))

    # 代理服务器
    proxyHost = "http-dyn.abuyun.com"
    proxyPort = "9020"
    # 代理隧道验证信息
    proxyUser = "H64W55P7KS0C4Q7D"
    proxyPass = "D03F3742C01C78A1"
    global proxy_auth_plugin_path
    proxy_auth_plugin_path = create_proxy_auth_extension(
        proxy_host=proxyHost,
        proxy_port=proxyPort,
        proxy_username=proxyUser,
        proxy_password=proxyPass)
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
    chromeOptions = webdriver.ChromeOptions()
    # 静默模式
    # chromeOptions.add_argument('headless')
    # chromeOptions.add_argument('--no-sandbox')
    # chromeOptions.add_argument('--disable-gpu')
    # chromeOptions.add_argument('--disable-dev-shm-usage')
    chromeOptions.add_extension(proxy_auth_plugin_path)
    driver = webdriver.Chrome(chrome_options=chromeOptions)

    # base_url = 'view-source:https://api.qimai.cn/andapp/appinfo?analysis=%s&appid=%s'
    base_url = 'https://api.qimai.cn/andapp/appinfo?analysis=%s&appid=%s'
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


def create_proxy_auth_extension(proxy_host, proxy_port,
                                proxy_username, proxy_password,
                                scheme='http', plugin_path=None):
    if plugin_path is None:
        plugin_path = r'D:/download/{}_{}@http-dyn.abuyun.com_9020.zip'.format(proxy_username, proxy_password)

    manifest_json = """
        {
            "version": "1.0.0",
            "manifest_version": 2,
            "name": "Abuyun Proxy",
            "permissions": [
                "proxy",
                "tabs",
                "unlimitedStorage",
                "storage",
                "<all_urls>",
                "webRequest",
                "webRequestBlocking"
            ],
            "background": {
                "scripts": ["background.js"]
            },
            "minimum_chrome_version":"22.0.0"
        }
        """

    background_js = string.Template(
        """
        var config = {
            mode: "fixed_servers",
            rules: {
                singleProxy: {
                    scheme: "${scheme}",
                    host: "${host}",
                    port: parseInt(${port})
                },
                bypassList: ["foobar.com"]
            }
          };
        chrome.proxy.settings.set({value: config, scope: "regular"}, function() {});
        function callbackFn(details) {
            return {
                authCredentials: {
                    username: "${username}",
                    password: "${password}"
                }
            };
        }
        chrome.webRequest.onAuthRequired.addListener(
            callbackFn,
            {urls: ["<all_urls>"]},
            ['blocking']
        );
        """
    ).substitute(
        host=proxy_host,
        port=proxy_port,
        username=proxy_username,
        password=proxy_password,
        scheme=scheme,
    )

    with zipfile.ZipFile(plugin_path, 'w') as zp:
        zp.writestr("manifest.json", manifest_json)
        zp.writestr("background.js", background_js)

    return plugin_path
