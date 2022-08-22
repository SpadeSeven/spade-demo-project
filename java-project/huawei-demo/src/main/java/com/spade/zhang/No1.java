package com.spade.zhang;

import java.util.Scanner;

public class No1 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // 路灯个数
    int size = sc.nextInt();

    int[] arr = new int[size];
    for (int index = 0; index < size; index++) {
      arr[index] = sc.nextInt();
    }

    for (int index = 0; index < size; index++) {
      // 当前长度
      int length = arr[index];
      // 超过100，跟其他位置的长度比较
      if (length > 100) {
        int a = length / 100;
        int b = length % 100;
        // 向前
        for (int i = 1; i <= a; i++) {
          int c = index + i;
          if (c > size - 1) {
            break;
          }
          int current = arr[c];
          if (current < (length - ((i) * 100))) {
            if ((length - ((i) * 100)) >=100){
              arr[c] = 100;
            }else {
              arr[c] = (length - ((i) * 100));
            }
          }
        }
        // 向后
        for (int i = 1; i <= a; i++) {
          int cIndex = index - i;
          if (cIndex < 0) {
            break;
          }
          int current = arr[cIndex];
          if (current < (length - ((i) * 100))) {
            if ((length - ((i) * 100)) >=100){
              arr[cIndex] = 100;
            }else {
              arr[cIndex] = (length - ((i) * 100));
            }
          }
        }
        // 自己
        arr[index] = 100;
      }
    }

    int count = 0;
    for (int index = 0; index < size - 1; index++) {
      int a = arr[index];
      int b = arr[index + 1];
      int c = a + b;
      if (c < 100) {
        count = count + (100 - c);
      }
    }

    System.out.println(count);
  }

}
