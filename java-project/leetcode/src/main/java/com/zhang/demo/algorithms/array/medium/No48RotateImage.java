package com.zhang.demo.algorithms.array.medium;

public class No48RotateImage {

  /**
   * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
   *
   * <p>你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
   *
   * @param matrix
   */
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    int[][] result = new int[n][n];
    for (int row = 0; row < matrix.length; row++) {
      for (int column = 0; column < matrix[row].length; column++) {
        result[column][n - 1 - row] = matrix[row][column];
      }
    }

    for (int row = 0; row < matrix.length; row++) {
      for (int column = 0; column < matrix[row].length; column++) {
        matrix[row][column] = result[row][column];
      }
    }
  }

  /**
   * 原地翻转
   *
   * @param matrix 二位数组
   */
  public void rotate1(int[][] matrix) {
    int n = matrix.length;
    for (int row = 0; row < matrix.length / 2; row++) {
      for (int column = 0; column < (matrix[row].length + 1) / 2; column++) {
        int temp = matrix[row][column];
        matrix[row][column] = matrix[n - 1 - column][row];
        matrix[n - 1 - column][row] = matrix[n - 1 - row][n - 1 - column];
        matrix[n - 1 - row][n - 1 - column] = matrix[column][n - 1 - row];
        matrix[column][n - 1 - row] = temp;
      }
    }
  }
}
