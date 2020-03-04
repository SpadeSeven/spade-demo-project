package com.zhang.test.java.day07;

import java.io.IOException;

/** Created by Administrator on 2018/6/3 0003. */
public class ExecTest {
  public static void main(String[] args) throws IOException {
    Runtime runtime = Runtime.getRuntime();

    runtime.exec("notepad.exe");
  }
}
