package com.zhang.offer.no12;

public class PathInTheMatrix {
  public boolean exist(char[][] board, String word) {

    char[] words = word.toCharArray();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        // 从矩阵的每一个位置访问一遍
        if (dfs(board, words, i, j, 0)) return true;
      }
    }

    return false;
  }

  /**
   * @param board 二维数组
   * @param word 目标字符串
   * @param i 行索引
   * @param j 列索引
   * @param k 字符串位置索引
   * @return
   */
  private boolean dfs(char[][] board, char[] word, int i, int j, int k) {
    if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word[k])
      return false;

    if (k == word.length - 1) return true;

    char tmp = board[i][j];
    board[i][j] = '/';
    boolean res =
        dfs(board, word, i + 1, j, k + 1)
            || dfs(board, word, i - 1, j, k + 1)
            || dfs(board, word, i, j + 1, k + 1)
            || dfs(board, word, i, j - 1, k + 1);
    board[i][j] = tmp;
    return res;
  }
}
