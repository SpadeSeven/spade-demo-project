package com.zhang.offer.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ2 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String word = br.readLine();
    char target = br.readLine().charAt(0);
    boolean targetIsletter = false;
    if (('A' <= target && target <= 'Z') || ('a' <= target && target <= 'z')) {
      targetIsletter = true;
    }
    int count = 0;
    for (int index = 0; index < word.length(); index++) {
      char ch = word.charAt(index);
      if (target == ch) {
        count++;
      } else if (targetIsletter) {
        if (target == (ch - 32) || target == (ch + 32)) {
          count++;
        }
      }
    }
    System.out.println(count);
  }
}
