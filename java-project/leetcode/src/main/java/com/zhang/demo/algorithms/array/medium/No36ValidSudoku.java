package com.zhang.demo.algorithms.array.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * <p>数字 1-9 在每一行只能出现一次。</>
 *
 * <p>数字 1-9 在每一列只能出现一次。
 *
 * <p>数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 */
public class No36ValidSudoku {

  /**
   * 有效的数独
   *
   * @param board 二维数组
   * @return 是否有效
   */
  public boolean isValidSudoku(char[][] board) {

    // 1. 数字 1-9 在每一行只能出现一次。
    for (int row = 0; row < board.length; row++) {
      Set<Character> numbers = new HashSet<>();
      for (int col = 0; col < board[row].length; col++) {
        char ch = board[row][col];
        if (ch != '.') {
          if (numbers.contains(ch)) {
            return false;
          } else {
            numbers.add(ch);
          }
        }
      }
    }

    // 2. 数字 1-9 在每一列只能出现一次。
    for (int col = 0; col < board.length; col++) {
      Set<Character> numbers = new HashSet<>();
      for (int row = 0; row < board[col].length; row++) {
        char ch = board[col][row];
        if (ch != '.') {
          if (numbers.contains(ch)) {
            return false;
          } else {
            numbers.add(ch);
          }
        }
      }
    }

    // 3. 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
    int row = 0;
    int col = 0;

    return true;
  }
}
