package com.zhang.demo.algorithms.string.medium;

public class No5LongestPalindrome {

  public String longestPalindrome(String s) {

    String longest = "";
    for (int i = 0; i < s.length(); i++) {
      for (int j = s.length() - 1; (j + 1 - i) > longest.length(); j--) {
        if (isPalindrome(s.substring(i, j + 1))) {
          longest = s.substring(i, j + 1);
        }
      }
    }

    return longest;
  }

  boolean isPalindrome(String s) {
    char[] ch = s.toCharArray();
    int start = 0;
    int end = ch.length - 1;
    while (start <= end) {
      if (ch[start] != ch[end]) {
        return false;
      }
      start++;
      end--;
    }

    return true;
  }
}
