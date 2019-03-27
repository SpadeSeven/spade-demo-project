package com.zhang.demo.algorithms.bitwise;

import javax.sound.midi.Soundbank;

/**
 * Created by Administrator on 2019-03-27.
 */
public class Example {

  public static void main(String[] args) {
    Example example = new Example();
    example.getPrime();
  }

  /**
   * 计算100以内的素数
   */
  public void getPrime() {
    int[] arr = new int[100];
    System.out.println("");
    boolean flag = false;
    for (int i = 2; i < 100; i++) {
      for (int j = 2; j <= Math.sqrt(i); j++) {
        if (i % j == 0) {
          flag = true;
          break;
        }
      }
      if (flag) {
        flag = false;
      }else {
        System.out.println(i);
      }
    }
  }

}
