package com.zhang.demo.algorithms.string.medium;

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
    int longest = 0;
    for (int i = 0; i < s.length(); i++) {
      char current = s.charAt(i);
      for (int j = i + 1; j < s.length(); j++) {
        if (current == s.charAt(j)){
          longest = j -i;
          break;
        }
      }
    }
    return longest;
  }
}
