package com.zhang.demo.algorithms.math.easy;

public class No9IsPalindrome {

  public boolean isPalindrome(int x) {

    if (x < 0) return false;

    int src = x;
    int pop = 0;
    int temp = 0;
    while (x != 0) {

      pop = x % 10;
      x = x / 10;

      temp = temp * 10 + pop;
    }

    return src == temp;
  }
}
