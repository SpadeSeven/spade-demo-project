package com.zhang.demo.algorithms.string.find;

public class RabinKarp implements Find {

  @Override
  public int find(String source, String target) {

    if (source == null && target == null) return 0;

    if (source == null || target == null || source.length() < target.length()) return -1;

    if (source.equals(target)) return 0;

    return -1;
  }
}
