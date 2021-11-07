package com.zhang.demo.algorithms.string.easy;

import java.util.HashMap;
import java.util.Map;

public class No3LongestSubstring {

  public int lengthOfLongestSubstring(String s) {
    int longest = 0;
    Map<Character, Integer> cache = new HashMap<>();
    int repeatedKeyIndex = 0;
    for (int index = 0; index < s.length(); index++) {
      char ch = s.charAt(index);
      // 1. 和之前的所有出现的字符比较是否重复
      // 1.1 如果重复的话，将之前出现的字符及之前的剔除；并与最大长度进行比较
      // 1.2 不重复的话，长度+1；
      if (cache.containsKey(ch) && cache.get(ch) >= repeatedKeyIndex) {
        longest = Integer.max(longest, index - repeatedKeyIndex);
        repeatedKeyIndex = cache.get(ch) + 1;
      }
      cache.put(ch, index);
    }
    // 处理最后无重复的结果
    longest = Integer.max(longest, s.length() - repeatedKeyIndex);
    return longest;
  }
}
