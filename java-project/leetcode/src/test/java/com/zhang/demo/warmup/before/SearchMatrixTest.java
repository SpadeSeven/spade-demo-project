package com.zhang.demo.warmup.before;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class SearchMatrixTest {

  private SearchMatrix searcher;

  @Before
  public void before() {
    searcher = new SearchMatrix();
  }

  @Test
  public void test() {
    int[][] matrix = {
      {1, 4, 7, 11, 15},
      {2, 5, 8, 12, 19},
      {3, 6, 9, 16, 22},
      {10, 13, 14, 17, 24},
      {18, 21, 23, 26, 30}
    };

    Assertions.assertThat(searcher.searchMatrix(matrix, 5)).isTrue();
    Assertions.assertThat(searcher.searchMatrix(matrix, 20)).isFalse();
  }

  @Test
  public void test_1() {
    int[][] matrix = {};

    Assertions.assertThat(searcher.searchMatrix(matrix, 0)).isFalse();
  }

  @Test
  public void test_2() {
    int[][] matrix = {{-5}};

    Assertions.assertThat(searcher.searchMatrix(matrix, -5)).isTrue();
  }
}
