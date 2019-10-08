package com.zhang.demo.warmup.before;

/**
 * 搜索二维矩阵 II
 *
 * <p>编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * <p>每行的元素从左到右升序排列。
 *
 * <p>每列的元素从上到下升序排列。
 *
 * <p>示例:
 *
 * <p>现有矩阵 matrix 如下：
 *
 * <p>[ [1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17, 24], [18, 21, 23,
 * 26, 30] ]
 *
 * <p>给定 target = 5，返回 true。
 *
 * <p>给定 target = 20，返回 false。
 */
public class SearchMatrix {

  public boolean searchMatrix(int[][] matrix, int target) {

    if (matrix.length == 0) {
      return false;
    }

    int row_length = matrix.length; // 行
    int col_length = matrix[0].length; // 列

    // 初始化指针，指向左下角
    int row = row_length - 1; // 行指针
    int col = 0; // 列指针
    while (row >= 0 && col < col_length) {
      if (matrix[row][col] > target) {
        row--;
      } else if (matrix[row][col] < target) {
        col++;
      } else {
        return true;
      }
    }

    return false;
  }
}
