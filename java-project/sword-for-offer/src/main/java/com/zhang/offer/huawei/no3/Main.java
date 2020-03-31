package com.zhang.offer.huawei.no3;

import java.util.Scanner;

/**
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 *
 * <p>长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 */
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String line = "";
    while (sc.hasNextLine()) {
      line = sc.nextLine();
      int len = line.length();
      char[] ch = new char[8];
      int size = len / 8;
      int remainder = len % 8;
    }
  }
}
