package com.zhang.offer.no16;

public class DoublePower {
  public double myPow(double x, int n) {

    if (x == 0) return 0;
    long b = n;
    if (n < 0) {
      x = 1 / x;
      b = -b;
    }

    double result = 1.0;

    while (b > 0) {
      if ((b & 1) == 1) result *= x;
      x *= x;
      b >>= 1;
    }

    return result;
  }
}
