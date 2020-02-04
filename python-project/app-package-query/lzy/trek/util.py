# -*- coding:utf-8 -*-

import argparse


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

