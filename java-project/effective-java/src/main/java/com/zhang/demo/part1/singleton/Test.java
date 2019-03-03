package com.zhang.demo.part1.singleton;

/**
 * Created by Administrator on 2019-02-26.
 */
public class Test {

  public static void main(String[] args) {
    long sum = 0L;
    long current = System.currentTimeMillis();
    for (long i = 0; i < Integer.MAX_VALUE; i++) {
      sum += i;
    }
    System.out.println(sum);
    System.out.println(System.currentTimeMillis() - current);
  }
}
