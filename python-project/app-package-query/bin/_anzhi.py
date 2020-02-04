# -*- coding:utf-8 -*-
import _load
import logging
import os
import sys
from lzy.trek import anzhi

workdir = os.getcwd()
if __name__ == '__main__':
    try:
        reload(sys)
        sys.setdefaultencoding('utf-8')
        anzhi.main(workdir)
    except:
        logging.error("main except")
        os._exit(1)
