package com.zhang.offer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HJ33 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = null;
    while ((str = br.readLine()) != null){
      String[] ip = str.split("\\.");
      long num = Long.parseLong(br.readLine());
      // 转10进制
      System.out.println(Long.parseLong(ip[0]) << 24 | Long.parseLong(ip[1]) << 16 | Long.parseLong(ip[2]) << 8 | Long.parseLong(ip[3]));

      // 转16进制
      StringBuilder builder = new StringBuilder();

    }
  }
}
