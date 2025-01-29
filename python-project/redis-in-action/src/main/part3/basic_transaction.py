# -*- coding: utf-8 -*-

import redis
import time
import threading

conn = redis.Redis(host="172.168.10.25", port=6378)


def nortans():
    print(conn.incr("nortans:"))
    time.sleep(1)
    conn.incr("nortans:", -1)


if __name__ == "__main__":
    if 1:
        for i in range(0, 3):
            threading.Thread(target=nortans()).start()
        time.sleep(0.5)

conn.close()
