# -*- coding:utf-8 -*-

import os


def main():
    package = set()
    for i in range(0, 8):
        with open(os.path.join('C:/work/myWorkNote/201912/app/query/anzhi/test-anzhi/out', 'out-%s.txt' % i), 'r',
                  encoding='utf-8') as anzhi:
            for line in anzhi:
                fields = line.split(u'\u0001')
                if len(fields) != 3:
                    continue
                package.add(fields[0])
        print(len(package))
    for i in range(0, 19):
        with open(os.path.join('C:/work/myWorkNote/201912/app/query/appchina/test-appchina/out', 'out-%s.txt' % i), 'r',
                  encoding='utf-8') as appchina:
            for line in appchina:
                fields = line.split(u'\u0001')
                if len(fields) != 3:
                    continue
                package.add(fields[0])
        print(len(package))
    with open('C:/work/myWorkNote/201912/app/query/package-no-name.txt', 'r', encoding='utf-8') as input, open(
            'C:/work/myWorkNote/201912/app/query/package-no-name_1.txt', 'w', encoding='utf-8') as out:
        for line in input:
            fields = line.split(',')
            if fields[0] not in package:
                out.write(line)


if __name__ == '__main__':
    main()
