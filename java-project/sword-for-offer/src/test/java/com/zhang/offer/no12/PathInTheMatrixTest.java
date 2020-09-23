package com.zhang.offer.no12;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class PathInTheMatrixTest {

  private PathInTheMatrix path;

  @Before
  public void setUp() {
    path = new PathInTheMatrix();
  }

  @Test
  public void test_exist_0() {
    char[][] board = {
      {'A', 'B', 'C', 'E'},
      {'S', 'F', 'C', 'S'},
      {'A', 'D', 'E', 'E'}
    };

    Assertions.assertThat(path.exist(board, "ABCCED")).isTrue();
  }

  @Test
  public void test_exist_1() {
    char[][] board = {
      {'a', 'b'},
      {'c', 'd'}
    };

    Assertions.assertThat(path.exist(board, "abcd")).isFalse();
  }
}
