package com.zhang.demo.algorithms.math.easy;

public class No7Reverse {

  public int reverse(int x) {
    int pop = 0;
    int result = 0;

    while (x != 0) {
      pop = x % 10;
      x = x / 10;

      // 最大值判断
      if (result > (Integer.MAX_VALUE / 10) || (result == (Integer.MAX_VALUE / 10) && pop > 7)) {
        return 0;
      }

      // 最小值判断
      if (result < (Integer.MIN_VALUE / 10) || (result == (Integer.MIN_VALUE / 10) && pop < -7)) {
        return 0;
      }

      // 容易溢出，需要之前做校验
      result = result * 10 + pop;
    }

    return result;
  }
}
