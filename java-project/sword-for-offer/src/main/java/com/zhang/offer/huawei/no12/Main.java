package com.zhang.offer.huawei.no12;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    Main main = new Main();

    int c = in.nextInt();
    int b = in.nextInt();
    HashMap<Integer, Integer> cache = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      int a = in.nextInt();
      int mod = main.sumOfByte(a) % b;
      if (mod < c) {
        if (cache.containsKey(mod)) {
          cache.put(mod, cache.get(mod) + 1);
        } else {
          cache.put(mod, 1);
        }
      }
    }

    int max = 0;
    for (Integer value : cache.values()) {
      max = Math.max(max, value);
    }

    System.out.println(max);
  }

  public int sumOfByte(int a) {

    String tmp = Integer.toHexString(a);
    char[] arr = tmp.toCharArray();
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += hexToint(arr[i]);
    }

    return sum;
  }

  public int hexToint(char a) {
    try {
      return Integer.parseInt(String.valueOf(a));
    } catch (Exception e) {
      if (a == 'a') {
        return 10;
      } else if (a == 'b') {
        return 11;
      } else if (a == 'c') {
        return 11;
      } else if (a == 'd') {
        return 11;
      } else if (a == 'e') {
        return 11;
      } else if (a == 'f') {
        return 11;
      }
    }

    return 0;
  }
}
