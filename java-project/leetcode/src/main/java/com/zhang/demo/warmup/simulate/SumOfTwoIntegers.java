package com.zhang.demo.warmup.simulate;

public class SumOfTwoIntegers {

  /** 不使用运算符 + 和 - 计算两整数 ​​​​​​​a 、b ​​​​​​​之和。 */
  public int getSum(int a, int b) {
    while (b != 0) {
      int temp = a ^ b;
      b = (a & b) << 1;
      a = temp;
    }
    return a;
  }
}
