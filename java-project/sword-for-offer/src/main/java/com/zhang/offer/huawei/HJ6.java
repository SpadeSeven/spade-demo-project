package com.zhang.offer.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ6 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int value = Integer.parseInt(br.readLine());
    while (true) {
      boolean find = false;
      for (int i = 2; i < (value + 1) / 2; i++) {
        // 找到质数
        if (value % i == 0) {
          System.out.print(i);
          System.out.print(" ");
          value = value / i;
          find = true;
          break;
        }
      }
      if (!find) {
        System.out.print(value);
        break;
      }
    }
  }
}
