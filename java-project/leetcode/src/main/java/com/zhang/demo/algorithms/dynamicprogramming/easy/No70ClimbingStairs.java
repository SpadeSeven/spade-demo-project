package com.zhang.demo.algorithms.dynamicprogramming.easy;

import java.util.HashMap;
import java.util.Map;

public class No70ClimbingStairs {

  private Map<Integer, Integer> cache = new HashMap<>();

  /**
   * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
   *
   * <p>每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   *
   * <p>注意：给定 n 是一个正整数。
   *
   * @param n 阶梯数
   * @return 方法数
   */
  public int climbStairs(int n) {

    if (cache.containsKey(n)) {
      return cache.get(n);
    }

    int value = 0;
    if (n == 1) {
      value = 1;
    } else if (n == 2) {
      value = 2;
    } else {
      value = climbStairs(n - 1) + climbStairs(n - 2);
    }

    cache.put(n, value);
    return value;
  }
}
