package com.zhang.offer.no17;

public class PrintNumbers {
  public int[] printNumbers(int n) {

    int nums = 1;
    for (int i = 1; i <= n; i++) {
      nums = nums * 10;
    }
    int[] arr = new int[nums - 1];
    for (int i = 1; i < nums; i++) {
      arr[i - 1] = i;
    }
    return arr;
  }
}
