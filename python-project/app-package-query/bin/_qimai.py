# -*- coding:utf-8 -*-
import logging
import os
import sys
import site

py_dir = os.path.dirname(os.path.realpath(__file__))

parent_dir = os.path.dirname(py_dir)

if os.path.isdir(os.path.join(parent_dir, 'src')):
    sys.path.append(parent_dir)

lib_dir = os.path.join(parent_dir, 'lib')

if os.path.isdir(lib_dir):
    old_len = len(sys.path)
    new_sys_path = []
    site.addsitedir(lib_dir)
    for item in sys.path[old_len:]:
        new_sys_path.append(item)
        sys.path.remove(item)
    sys.path[:0] = new_sys_path

os.chdir(parent_dir)

workdir = os.getcwd()
if __name__ == '__main__':
    try:
        from lzy.trek import qimai

        qimai.main(workdir)
    except:
        logging.error("main except")
        os._exit(1)
