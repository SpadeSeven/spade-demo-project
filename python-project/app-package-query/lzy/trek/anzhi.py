# -*- coding:utf-8 -*-
import logging
import requests
import util
import os
import sys
from lxml import html

etree = html.etree


# 安智
def main(workdir):
    options = util._handle_cmd_line(sys.argv[1:])

    logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
                        filename=os.path.join(workdir, 'logs', options.log_name))
    # http://www.anzhi.com/pkg/tv.danmaku.bili
    base_url = 'http://www.anzhi.com/pkg/'
    with open(options.input_file, 'r') as input_file, \
            open(options.out_file, 'w') as out_file, \
            open(options.out_not_find_file, 'w') as out_not_find_file:
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
    res = requests.get(url)

    if 200 != res.status_code:
        raise Exception('url : %s cannot find, status_code: %s' % (url, res.status_code))
    # package_name
    # cetagory
    category = ''
    other = etree.HTML(res.content).xpath('//*[@id="detail_line_ul"]/li/text()')
    for i in other:
        # <p class="art-content">分类：便捷生活</p>
        if '分类' == i.split("：")[0]:
            category = i.split("：")[1]
            break
    # appname
    appname = etree.HTML(res.content).xpath('//div[@class="detail_line"]/h3/text()')[0]
    # icon
    # /icon.php?u=ZGF0YTV8aWNvbnwyMDE5MDd8MTh8b2NwMVNEcHRIa1dkcW9ZNlVRd2wxeFc5NHRDZWJ0QlY2bUMzOC9PdC9DWnU=
    img_url = etree.HTML(res.content).xpath('//div[@class="detail_icon"]/img/@src')[0]
    img_url = 'http://www.anzhi.com/' + str(img_url)
    download_img(str(img_url), options.out_icon_path, package_name)

    result = []
    result.append(package_name)
    result.append(str(category))
    result.append(str(appname))
    return u'\u0001'.join(result)


def download_img(img_url, out_path, package_name):
    res = requests.get(img_url)
    file = open(os.path.join(out_path, package_name + '.png'), 'wb')
    file.write(res.content)
    file.close()
