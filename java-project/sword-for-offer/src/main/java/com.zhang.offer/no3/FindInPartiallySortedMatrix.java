package com.zhang.offer.no3;

/** Created by Administrator on 2019-04-01. */
public class FindInPartiallySortedMatrix {

  private int num = 0;

  private int num_1 = 0;

  public static void main(String[] args) {

    FindInPartiallySortedMatrix find = new FindInPartiallySortedMatrix();
    int[][] matrix = {
      {1, 2, 8, 9},
      {2, 4, 9, 12},
      {4, 7, 10, 13},
      {6, 8, 11, 15}
    };

    System.out.println(find.find(matrix, 0));
    System.out.println(find.find(matrix, 2));
    System.out.println(find.find(matrix, 4));
    System.out.println(find.find(matrix, 9));
    System.out.println(find.find(matrix, 10));
    System.out.println(find.find(matrix, 12));
    System.out.println(find.find(matrix, 18));

    System.out.println(find.find_1(matrix, 0));
    System.out.println(find.find_1(matrix, 2));
    System.out.println(find.find_1(matrix, 4));
    System.out.println(find.find_1(matrix, 9));
    System.out.println(find.find_1(matrix, 10));
    System.out.println(find.find_1(matrix, 12));
    System.out.println(find.find_1(matrix, 18));

    System.out.println(find.num);
    System.out.println(find.num_1);
  }

  public boolean find(int[][] matrix, int b) {
    if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
      return false;
    }

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        num++;
        if (matrix[i][j] < b) {
          continue;
        } else if (matrix[i][j] > b) {
          break;
        } else {
          return true;
        }
      }
    }
    return false;
  }

  public boolean find_1(int[][] matrix, int number) {

    // 输入条件判断
    if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
      return false;
    }

    int rows = matrix.length; // 数组的行数
    int cols = matrix[1].length; // 数组行的列数

    int row = 0; // 起始开始的行号
    int col = cols - 1; // 起始开始的列号

    // 要查找的位置确保在数组之内
    while (row >= 0 && row < rows && col >= 0 && col < cols) {
      num_1++;
      if (matrix[row][col] == number) { // 如果找到了就直接退出
        return true;
      } else if (matrix[row][col] > number) { // 如果找到的数比要找的数大，说明要找的数在当前数的左边
        col--; // 列数减一，代表向左移动
      } else { // 如果找到的数比要找的数小，说明要找的数在当前数的下边
        row++; // 行数加一，代表向下移动
      }
    }

    return false;
  }
}
