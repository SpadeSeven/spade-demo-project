package com.zhang.offer.no10;

/**
 * Created by Administrator on 2019-04-16.
 *
 * 请实现一个函数，输入一个整数，输出该整数二进制中1的个数。
 * 例如：输入9(二进制：1001)，输出为2
 */
public class NumberOf1InBinary {

  public static void main(String[] args) {
    NumberOf1InBinary numberOf1InBinary = new NumberOf1InBinary();
    System.out.println(numberOf1InBinary.Numberof1_1(9));
    System.out.println(numberOf1InBinary.Numberof1_1(1));
    System.out.println(numberOf1InBinary.Numberof1_1(0x7FFFFFFF));
    System.out.println(numberOf1InBinary.Numberof1_1(0x80000000));
    System.out.println(numberOf1InBinary.Numberof1_1(0xFFFFFFFF));
    System.out.println(numberOf1InBinary.Numberof1_1(0));

    System.out.println(numberOf1InBinary.numberof1_2(9));
    System.out.println(numberOf1InBinary.numberof1_2(1));
    System.out.println(numberOf1InBinary.numberof1_2(0x7FFFFFFF));
    System.out.println(numberOf1InBinary.numberof1_2(0x80000000));
    System.out.println(numberOf1InBinary.numberof1_2(0xFFFFFFFF));
    System.out.println(numberOf1InBinary.numberof1_2(0));
  }

  // 常规解法
  public int Numberof1_1(int num) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
      if (((num >> i) & 1) == 1) {
        count += 1;
      }
    }
    return count;
  }

  public int numberof1_2(int num) {
    int count = 0;
    while (num != 0) {
      count++;
      num = (num - 1) & num;
    }

    return count;
  }

}
