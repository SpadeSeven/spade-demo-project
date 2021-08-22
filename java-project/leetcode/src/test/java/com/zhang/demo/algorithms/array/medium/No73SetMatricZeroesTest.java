package com.zhang.demo.algorithms.array.medium;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No73SetMatricZeroesTest {

  private No73SetMatricZeroes setter;

  @Before
  public void setUp() {
    setter = new No73SetMatricZeroes();
  }

  @Test
  public void test_SetZeroes_0() {
    int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};

    setter.setZeroes(matrix);
    int[][] expected = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
    Assertions.assertThat(matrix).isEqualTo(expected);
  }

  @Test
  public void test_SetZeroes_1() {
    int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};

    setter.setZeroes(matrix);
    int[][] expected = {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}};
    Assertions.assertThat(matrix).isEqualTo(expected);
  }
}
