package com.zhang.offer.no10;

import java.util.HashMap;

public class FibonacciSequence {

  private HashMap<Integer, Integer> cache = new HashMap<>();

  public int fib(int n) {

    if (cache.containsKey(n)) return cache.get(n);
    if (n == 0) return 0;
    if (n == 1) return 1;

    int result = (fib(n - 1) + fib(n - 2)) % 1000000007;

    cache.put(n, result);
    return result;
  }
}
