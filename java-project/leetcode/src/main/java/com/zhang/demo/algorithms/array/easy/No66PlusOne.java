package com.zhang.demo.algorithms.array.easy;

public class No66PlusOne {

  public int[] plusOne(int[] digits) {

    // 进数
    int temp = 0;
    int result = 0;
    for (int index = digits.length - 1; index >= 0; index--) {
      if (index == digits.length - 1) {
        result = digits[index] + 1;
      } else {
        result = digits[index] + temp;
      }

      digits[index] = result % 10;
      temp = result / 10;
    }
    if (temp == 1) {
      digits = new int[digits.length + 1];
      digits[0] = 1;
    }

    return digits;
  }
}
