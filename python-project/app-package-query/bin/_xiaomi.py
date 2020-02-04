# -*- coding:utf-8 -*-
import logging
import os
from lzy.trek import xiaomi


if __name__ == '__main__':
    try:
        xiaomi.main()
    except:
        logging.error("main except")
        os._exit(1)
