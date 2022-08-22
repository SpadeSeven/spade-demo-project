package com.zhang.offer.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ3 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String first = br.readLine();
    int size = Integer.parseInt(first);
    int[] arr = new int[500];
    for (int index = 0; index < size; index++) {
      String line = br.readLine();
      int value = Integer.parseInt(line);
      arr[value] = value;
    }

    for (int value : arr) {
      if (value != 0) {
        System.out.println(value);
      }
    }
  }
}
