package com.zhang.demo.algorithms.string.easy;

public class No14LongestCommonPrefix {

  public String longestCommonPrefix(String[] strs) {

    if (null == strs || strs.length <= 0) {
      return "";
    }

    int index = 0;
    while (true) {
      char ch = '\u0000';
      for (int i = 0; i < strs.length; i++) {

        if (index >= strs[i].length()) {
          return strs[i].substring(0, index);
        }

        if (i == 0) {
          ch = strs[i].charAt(index);
        } else if (ch != strs[i].charAt(index)) {
          return strs[i].substring(0, index);
        }
      }

      index++;
    }
  }
}
