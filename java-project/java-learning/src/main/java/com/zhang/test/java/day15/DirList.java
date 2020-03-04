package com.zhang.test.java.day15;

import java.io.File;

/** Created by Administrator on 2018/6/6 0006. */
class DirFilter {}

public class DirList {

  public static void main(String[] args) {
    File path = new File(".");
    String[] list;
    if (args.length == 0) {
      list = path.list();
    } else {
      //      list = path.list(new (args[0]));
    }
  }
}
