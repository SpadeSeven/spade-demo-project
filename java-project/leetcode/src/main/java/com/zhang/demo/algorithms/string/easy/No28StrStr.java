package com.zhang.demo.algorithms.string.easy;

public class No28StrStr {

  public int strStr(String haystack, String needle) {

    for (int indexI = 0; indexI < haystack.length(); indexI++) {
      if (haystack.length() - indexI < needle.length()) {
        return -1;
      }

      boolean finded = true;
      for (int indexJ = 0; indexJ < needle.length(); indexJ++) {
        char chI = haystack.charAt(indexI + indexJ);
        char chJ = needle.charAt(indexJ);
        if (chI != chJ) {
          finded = false;
          break;
        }
      }
      if (finded) {
        return indexI;
      }
    }
    return -1;
  }
}
