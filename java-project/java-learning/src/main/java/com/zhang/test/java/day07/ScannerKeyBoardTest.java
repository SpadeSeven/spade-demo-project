package com.zhang.test.java.day07;

import java.util.Scanner;

/** Created by Administrator on 2018/6/3 0003. */
public class ScannerKeyBoardTest {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      System.out.println("键盘输入的内容是:" + sc.next());
    }
  }
}
