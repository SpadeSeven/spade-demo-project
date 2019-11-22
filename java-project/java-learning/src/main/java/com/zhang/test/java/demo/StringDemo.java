package com.zhang.test.java.demo;

import com.google.common.base.Charsets;

public class StringDemo {

  public static void main(String[] args) {

    StringDemo demo = new StringDemo();
    //demo.getbyte("hello");
    //
    demo.getbyte("笑");
//    System.out.println("笑".length());
    //
    System.out.println("😀".length());
    demo.getbyte("😀");
  }

  private void getbyte(String str) {
    byte[] bytes = str.getBytes(Charsets.UTF_8);
    System.out.println(bytes.length);
    for (byte b : bytes) {
      System.out.println(b);
    }
  }

}
