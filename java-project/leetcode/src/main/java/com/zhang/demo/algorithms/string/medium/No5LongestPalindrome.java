package com.zhang.demo.algorithms.string.medium;

public class No5LongestPalindrome {

  public String longestPalindrome(String s) {

    String longest = "";
    // 奇数回文
    for (int center = 0; center < s.length(); center++) {
      String res = expandAroundCenter(s, center, center);
      if (res.length() > longest.length()) {
        longest = res;
      }
    }

    // 偶数回文
    for (int center = 0; center < s.length() - 1; center++) {
      String res = expandAroundCenter(s, center, center + 1);
      if (res.length() > longest.length()) {
        longest = res;
      }
    }

    return longest;
  }

  public String expandAroundCenter(String s, int start, int end) {

    while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
      start--;
      end++;
    }

    return s.substring(start + 1, end);
  }

  /**
   * 动态规划
   *
   * @param s 输入字符串
   * @return 最长回文子串
   */
  public String dp(String s) {

    char[] chars = s.toCharArray();

    boolean[][] dp = new boolean[chars.length][chars.length];

    String longest = "";

    // 步长
    for (int step = 0; step < chars.length; ++step) {
      // 子串的起始坐标
      for (int start = 0; start + step < chars.length; ++start) {
        // 子串的结束坐标
        int end = start + step;

        // 步长为0：子串只有字符本身，恒为true
        // 步长为1：字串有两个字符，比较两个字符是否相等即可
        // 步长> 1：查看去掉首尾字符的子串是否是回文数，并且判断首尾字符是否相等
        if (step == 0) {
          dp[start][end] = true;
        } else if (step == 1) {
          dp[start][end] = (chars[start] == chars[end]);
        } else {
          dp[start][end] = (dp[start + 1][end - 1] && chars[start] == chars[end]);
        }

        // 当前子串是回文串
        // 子串长度(步长+1) 大于之前的最大长度
        if (dp[start][end] && step + 1 > longest.length()) {
          longest = s.substring(start, end + 1);
        }
      }
    }

    return longest;
  }
}
