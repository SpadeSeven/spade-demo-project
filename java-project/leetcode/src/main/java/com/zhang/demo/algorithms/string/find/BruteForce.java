package com.zhang.demo.algorithms.string.find;

public class BruteForce implements Find {

  @Override
  public int find(String source, String target) {

    if (source == null && target == null) return 0;

    if (source == null || target == null || source.length() < target.length()) return -1;

    if (source.equals(target)) return 0;

    for (int s_index = 0; s_index < source.length() - target.length(); s_index++) {
      for (int t_index = 0; t_index < target.length(); t_index++) {
        if (source.charAt(s_index + t_index) != target.charAt(t_index)) break;

        if (t_index == target.length() - 1) return s_index;
      }
    }

    return -1;
  }
}
