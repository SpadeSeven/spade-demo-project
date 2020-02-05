# -*- coding:utf-8 -*-
import logging
import requests
from lzy.trek import util
from lxml import etree
import os
import sys
from fake_useragent import UserAgent


# 小米
def main(workdir):
    options = util._handle_cmd_line(sys.argv[1:])

    logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
                        filename=os.path.join(workdir, 'logs', options.log_name))
    # http://app.flyme.cn/apps/public/detail?package_name=io.moreless.tide
    base_url = 'http://app.flyme.cn/apps/public/detail?package_name='
    with open(options.input_file, 'r', encoding='utf-8') as input_file, \
            open(options.out_file, 'w', encoding='utf-8') as out_file, \
            open(options.out_not_find_file, 'w', encoding='utf-8') as out_not_find_file:
        count_all = 0
        count_find = 0
        count_not_find = 0
        for line in input_file:
            fields = line.strip().split(options.input_file_field_separator)
            package_name = fields[options.input_file_package_index]
            real_url = base_url + package_name
            try:
                out_file.write(process(real_url, options, package_name))
                out_file.write('\n')
                count_find += 1
            except:
                out_not_find_file.write(line.strip())
                out_not_find_file.write('\n')
                count_not_find += 1
            count_all += 1
            if count_all % 100 == 0:
                logging.info("procee line: %s, find package: %s, not find %s" % (count_all, count_find, count_not_find))
    logging.info('over ,exit')


def process(url, options, package_name):
    headers = {}
    ua = UserAgent()
    headers['User-Agent'] = ua.chrome
    res = requests.get(url, headers=headers, timeout=60)

    if 200 != res.status_code:
        raise Exception('url : %s cannot find, status_code: %s' % (url, res.status_code))
    # package_name
    # cetagory
    category = etree.HTML(res.content).xpath('//*[@class="app_content"]/a/text()')[0]
    # appname
    appname = etree.HTML(res.content).xpath('//*[@class="detail_top"]/h3/text()')[0]
    # icon
    img_url = \
        etree.HTML(res.content).xpath('//div[@class="app_download download_container"]/img[@class="app_img"]/@src')[0]
    download_img(str(img_url), options.out_icon_path, package_name)

    result = []
    result.append(package_name)
    result.append(str(category))
    result.append(str(appname))
    return u'\u0001'.join(result)


def download_img(img_url, out_path, package_name):
    headers = {}
    ua = UserAgent()
    headers['User-Agent'] = ua.chrome
    res = requests.get(img_url, headers=headers, timeout=60)
    res = requests.get(img_url, timeout=60)
    file = open(os.path.join(out_path, package_name + '.png'), 'wb')
    file.write(res.content)
    file.close()
