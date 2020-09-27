package com.zhang.offer.no15;

public class OneInBinary {

  // you need to treat n as an unsigned value
  public int hammingWeight(int n) {
    int sum = 0;
    for (int i = 0; i < 32; i++) {
      int t = n & 1;
      if (t == 1) {
        sum += 1;
      }
      n = n >> 1;
    }

    return sum;
  }
}
