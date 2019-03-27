package com.zhang.demo.algorithms.bitwise;

/**
 * Created by Administrator on 2019-03-27.
 */
public class Demo {

  public static void main(String[] args) {
    Demo demo = new Demo();
    // 判断奇偶
    demo.isOdd();

    // 交换两数
    demo.swap(13, 6);
    demo.swap(-13, -6);
    demo.swap(13, -6);
    demo.swap_1(13, 6);
    demo.swap_1(-13, -6);
    demo.swap_1(13, -6);

    // 变换符号
    demo.signReversal(11);
    demo.signReversal(-11);
    demo.signReversal(0);

    // 取绝对值
    demo.abs(11);
    demo.abs(0);
    demo.abs(-11);
    demo.abs_1(11);
    demo.abs_1(0);
    demo.abs_1(-11);

  }

  /**
   * 判断奇偶
   */

  public void isOdd() {
    for (int i = 0; i <= 100; i++) {
      if ((i & 1) == 1) {
        System.out.println(i);
      }
    }
  }

  /**
   * 交换两数
   */
  public void swap(int a, int b) {
    if (a != b) {
      a ^= b;
      b ^= a;
      a ^= b;
    }
    System.out.println("a: " + a + " b : " + b);
  }

  /**
   * 交换两数，加法写法
   */
  public void swap_1(int a, int b) {
    if (a != b) {
      a = a - b;
      b = b + a;
      a = b - a;
    }
    System.out.println("a: " + a + " b : " + b);
  }

  public void signReversal(int a) {
    System.out.println(~a + 1);
  }

  /**
   * 取绝对值
   */
  public void abs(int a) {
    System.out.println((a >> 31) == 0 ? a : (~a + 1));
  }

  public void abs_1(int a) {
    int i = a >> 31;
    System.out.println((a ^ i) - i);
  }
}
