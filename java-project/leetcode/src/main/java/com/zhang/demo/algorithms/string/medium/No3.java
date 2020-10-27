package com.zhang.demo.algorithms.string.medium;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 *
 * <p>给定一个字符串，请你找出其中不含有重复字符的最长子串的长度
 *
 * <p>示例1： 输入："abcabcbb" 输出：3
 *
 * <p>示例2： 输入："bbbbbb" 输出：1
 *
 * <p>示例3： 输入："pwwkew" 输出：3
 */
public class No3 {

  public int lengthOfLongestSubstring(String s) {
    char[] ch = s.toCharArray();

    HashMap<Character, Integer> cache = new HashMap<>();

    int longest = 0;
    int start = 0;
    for (int index = 0; index < ch.length; index++) {
      // 1、判断是否存在
      // 2、判断是否在有效范围
      if (cache.containsKey(ch[index]) && cache.get(ch[index]) >= start) {
        // 计算当前长度并与之前的长度比较
        longest = Math.max(longest, index - start);
        start = cache.get(ch[index]) + 1;
        cache.put(ch[index], index);
      } else {
        cache.put(ch[index], index);
      }
    }

    longest = Math.max(longest, ch.length - start);

    return longest;
  }
}
