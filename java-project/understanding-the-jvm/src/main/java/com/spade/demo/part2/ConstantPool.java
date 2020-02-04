package com.spade.demo.part2;

public class ConstantPool {

  public static void main(String[] args) {
    String a = "abc";
    String b = "abc";
    String c = new StringBuilder("abc").toString();
    String d = "a" + "bc";
    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(a == d);
  }
}
