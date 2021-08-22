package com.zhang.demo.algorithms.array.medium;

public class No73SetMatricZeroes {

  /**
   * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
   *
   * @param matrix 矩阵
   */
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int[][] result = new int[m][n];

    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        if (matrix[row][col] == 0) {
          for (int i = 0; i < n; i++) {
            result[row][i] = 2;
          }
        } else {
          if (result[row][col] != 2) {
            result[row][col] = 1;
          }
        }
      }
    }
  }
}
