#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
    create zip 安装包(egg)
"""

import logging
import os.path
import shutil
import sys

current_file_dir = os.path.dirname(__file__)
py_strip_dirs = open(
    os.path.join(current_file_dir, "strip_py.list")).readlines()


def _copytree(src, dst, ignore=None):
    names = os.listdir(src)
    if ignore is not None:
        ignored_names = ignore(src, names)
    else:
        ignored_names = set()
    try:
        os.makedirs(dst)
    except Exception:
        pass
    errors = []
    for name in names:
        if name in ignored_names:
            continue
        srcname = os.path.join(src, name)
        dstname = os.path.join(dst, name)
        try:
            if os.path.isdir(srcname):
                shutil.copytree(srcname, dstname, ignore=ignore)
            else:
                # Will raise a SpecialFileError for unsupported file types
                shutil.copy2(srcname, dstname)
        # catch the Error from the recursive copytree so that we can
        # continue with other files
        except shutil.Error as err:  # noqa
            errors.extend(err.args[0])
        except EnvironmentError as why:  # noqa
            errors.append((srcname, dstname, str(why)))
    try:
        shutil.copystat(src, dst)
    except OSError as why:  # noqa
        if WindowsError is not None and isinstance(why, WindowsError):
            # Copying file access times may fail on Windows
            pass
        else:
            errors.extend((src, dst, str(why)))
    if errors:
        raise shutil.Error(errors)


def _zip_file(target_dir):
    root_dir = os.path.dirname(target_dir)
    os.chdir(root_dir)
    shutil.make_archive(target_dir, format="gztar",
                        root_dir=root_dir, base_dir=os.path.basename(target_dir)
                        )
    # shutil.move(target_dir + ".zip", target_dir + ".egg")


def _strip_py(py_dir):
    for base, dirs, files in os.walk(py_dir):  # noqa
        for name in files:
            if name.endswith('.py'):
                path = os.path.join(base, name)
                logging.debug("Deleting %s", path)
                os.unlink(path)


def main():
    # src_dir = sys.argv[1]
    site_pacakge_dir = sys.argv[2]
    target_dir = sys.argv[3]

    top_dir = sys.argv[4]

    shutil.rmtree(target_dir, ignore_errors=True)
    os.makedirs(target_dir)

    for dir in ('bin', 'logs'):
        _copytree(os.path.join(top_dir, dir), os.path.join(target_dir, dir))

    target_lib_dir = os.path.join(target_dir, 'lib')
    _copytree(site_pacakge_dir, target_lib_dir)

    for py_dir in py_strip_dirs:
        _strip_py(os.path.join(target_dir, py_dir))

    _zip_file(target_dir)


if __name__ == '__main__':
    try:
        main()
    except Exception:
        logging.exception("main except")
        sys.exit(1)
