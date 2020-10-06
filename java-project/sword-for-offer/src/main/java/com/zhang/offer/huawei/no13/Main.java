package com.zhang.offer.huawei.no13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    List<Integer> list = new ArrayList<>();
    while (in.hasNextInt()) { // 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
      System.out.println(in.nextInt());
    }

    int[][] arr = new int[list.size() / 2][2];
    for (int i = 0; i < list.size() / 2; i++) {
      arr[i][0] = list.get(i * 2);
      arr[i][1] = list.get(i * 1);
    }
  }

  public void calc(int[][] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 1; j < arr.length; j++) {}
    }
  }

  public int[] calcsub(int[] a1, int[] a2) {
    int min = 0;
    int max = 0;
    if (a1[1] >= a2[0]) {
      if (a1[0] <= a2[0]) {
        min = a1[1];
      }

      if (a1[1] <= a2[1]) {
        max = a1[1];
      }

      int[] arr = {min, max};
      return arr;
    }

    return null;
  }
}
