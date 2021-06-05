package com.zhang.demo.algorithms.array.medium;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No36ValidSudokuTest {

  private No36ValidSudoku no36ValidSudoku;

  @Before
  public void setUp() {
    no36ValidSudoku = new No36ValidSudoku();
  }

  @Test
  public void test_IsValidSudoku_0() {
    char[][] board = {
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    Assertions.assertThat(no36ValidSudoku.isValidSudoku(board)).isTrue();
  }

  @Test
  public void test_IsValidSudoku_1() {
    char[][] board = {
      {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    Assertions.assertThat(no36ValidSudoku.isValidSudoku(board)).isFalse();
  }
}
