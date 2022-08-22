package com.zhang.offer.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ4 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = br.readLine();
    StringBuffer buffer = new StringBuffer();
    int count = 0;
    for (int index = 0; index < line.length() ; index++) {
      buffer.append(line.charAt(index));
      count++;

      if (count % 8 == 0){
        System.out.println(buffer.toString());
        buffer = new StringBuffer();
      }
    }
    while (count % 8 != 0){
      buffer.append("0");
      count++;
    }
    System.out.println(buffer.toString());
  }
}
