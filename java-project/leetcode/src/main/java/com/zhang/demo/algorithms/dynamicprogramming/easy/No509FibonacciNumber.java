package com.zhang.demo.algorithms.dynamicprogramming.easy;

import java.util.HashMap;
import java.util.Map;

public class No509FibonacciNumber {

  private Map<Integer, Integer> cache = new HashMap<>();

  /**
   * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
   *
   * <p>F(0) = 0，F(1) = 1 F(n) = F(n - 1) + F(n - 2)，其中 n > 1
   *
   * @param n
   * @return
   */
  public int fib(int n) {

    if (cache.containsKey(n)) {
      return cache.get(n);
    }

    int value = 0;
    if (n == 0) {
      value = 0;
    } else if (n == 1) {
      value = 1;
    } else {
      value = fib(n - 1) + fib(n - 2);
    }

    cache.put(n, value);
    return value;
  }
}
