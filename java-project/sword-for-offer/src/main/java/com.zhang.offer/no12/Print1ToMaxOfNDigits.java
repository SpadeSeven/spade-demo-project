package com.zhang.offer.no12;

/** 题12: 输入数字n，按顺序打印出从1到最大到N位十进制数， 比如：输入3， 则打印1、2、3......999. */
public class Print1ToMaxOfNDigits {

  public static void main(String[] args) {
    Print1ToMaxOfNDigits maxOfNDigits = new Print1ToMaxOfNDigits();
    maxOfNDigits.print1ToMaxOfNDigits(2);
  }

  public void print1ToMaxOfNDigits(int n) {
    int max_digit = 1;
    for (int i = 0; i < n; i++) {
      max_digit = 10 * max_digit;
    }
    for (int i = 1; i < max_digit; i++) {
      System.out.println(i);
    }
  }
}
