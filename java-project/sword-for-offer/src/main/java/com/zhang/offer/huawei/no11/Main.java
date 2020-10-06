package com.zhang.offer.huawei.no11;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 1、生成二维数组
    int n = in.nextInt();
    int m = in.nextInt();
    int[][] arr = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        arr[i][j] = in.nextInt();
      }
    }

    // 计算最大子矩阵
    Main main = new Main();
    System.out.println(main.maxSum(arr));
  }

  public int maxSum(int[][] arr) {

    // 遍历每一个点作为矩阵的起始点
    int maxsum = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        maxsum = Math.max(maxsum, maxSubArr(arr, i, j));
      }
    }
    return maxsum;
  }

  // 求每个点的最大子矩阵
  public int maxSubArr(int[][] arr, int n, int m) {
    int maxsum = 0;
    for (int i = n; i < arr.length; i++) {
      for (int j = m; j < arr[i].length; j++) {
        maxsum = Math.max(maxsum, sumOfSubArr(arr, n, m, i, j));
      }
    }
    return maxsum;
  }

  /** 求子矩阵之和 */
  public int sumOfSubArr(int[][] arr, int n, int m, int a, int b) {

    int sum = 0;

    for (int i = n; i < a + 1; i++) {
      for (int j = m; j < b + 1; j++) {
        sum += arr[i][j];
      }
    }
    return sum;
  }
}
