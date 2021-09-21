package com.zhang.demo.algorithms.dynamicprogramming.easy;

import java.util.HashMap;
import java.util.Map;

public class No1137NthTribonacciNumber {

  private Map<Integer, Integer> cache = new HashMap<>();

  /**
   * 泰波那契序列 Tn 定义如下：
   *
   * <p>T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
   *
   * <p>给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
   *
   * @param n
   * @return
   */
  public int tribonacci(int n) {
    if (cache.containsKey(n)) {
      return cache.get(n);
    }

    int value = 0;
    if (n == 0) {
      value = 0;
    } else if (n == 1) {
      value = 1;
    } else if (n == 2) {
      value = 1;
    } else {
      value = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    cache.put(n, value);
    return value;
  }
}
