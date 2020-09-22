package com.zhang.offer.no10;

import java.util.HashMap;

public class FrogJump {

  private HashMap<Integer, Integer> cache = new HashMap<>();

  public int numWays(int n) {
    if (cache.containsKey(n)) return cache.get(n);
    if (n == 0) return 1;
    if (n == 1) return 1;

    int result = (numWays(n - 1) + numWays(n - 2)) % 1000000007;
    cache.put(n, result);
    return result;
  }
}
