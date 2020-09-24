package com.zhang.offer.no13;

public class RobotMove {

  public int movingCount(int m, int n, int k) {

    boolean[][] visited = new boolean[m][n];
    return dfs(m, n, k, 0, 0, visited);
  }

  public int dfs(int m, int n, int k, int row, int column, boolean[][] visited) {
    if (row > m - 1
        || row < 0
        || column > n - 1
        || column < 0
        || !validNum(row, column, k)
        || visited[row][column]) return 0;
    visited[row][column] = true;

    return 1 + dfs(m, n, k, row + 1, column, visited) + dfs(m, n, k, row, column + 1, visited);
  }

  boolean validNum(int a, int b, int target) {
    return getNumSum(a) + getNumSum(b) <= target;
  }

  /**
   * 求一个数的位数之和
   *
   * @param a 目标数字
   * @return 位数之和
   */
  int getNumSum(int a) {
    int sum = 0;
    while (a > 0) {
      int s = a % 10;
      a = a / 10;
      sum = sum + s;
    }
    return sum;
  }
}
