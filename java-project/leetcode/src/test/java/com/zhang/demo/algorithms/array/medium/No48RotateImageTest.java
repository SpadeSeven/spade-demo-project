package com.zhang.demo.algorithms.array.medium;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No48RotateImageTest {

  private No48RotateImage rotater;

  @Before
  public void setUp() {
    rotater = new No48RotateImage();
  }

  @Test
  public void test_Rotate_1() {
    int[][] matrix = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}
    };

    rotater.rotate(matrix);
    int[][] expected = {
      {7, 4, 1},
      {8, 5, 2},
      {9, 6, 3}
    };

    Assertions.assertThat(matrix).isEqualTo(expected);
  }

  @Test
  public void test_Rotate_2() {
    int[][] matrix = {
      {5, 1, 9, 11},
      {2, 4, 8, 10},
      {13, 3, 6, 7},
      {15, 14, 12, 16}
    };

    rotater.rotate(matrix);
    int[][] expected = {
      {15, 13, 2, 5},
      {14, 3, 4, 1},
      {12, 6, 8, 9},
      {16, 7, 10, 11}
    };

    Assertions.assertThat(matrix).isEqualTo(expected);
  }

  @Test
  public void test_Rotate_3() {
    int[][] matrix = {{1}};

    rotater.rotate(matrix);
    int[][] expected = {{1}};

    Assertions.assertThat(matrix).isEqualTo(expected);
  }

  @Test
  public void test_Rotate_4() {
    int[][] matrix = {
      {1, 2},
      {3, 4}
    };

    rotater.rotate(matrix);
    int[][] expected = {
      {3, 1},
      {4, 2}
    };

    Assertions.assertThat(matrix).isEqualTo(expected);
  }
}
