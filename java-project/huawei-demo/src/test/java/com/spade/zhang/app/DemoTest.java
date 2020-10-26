package com.spade.zhang.app;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DemoTest {

  @Test
  public void test_Search() {
    Demo demo = new Demo();

    int[][] matrix = {
        {1, 3, 5, 7},
        {10, 11, 16, 20},
        {23, 30, 34, 50}};

    Assertions.assertThat(demo.search(matrix, 3)).isTrue();
    Assertions.assertThat(demo.search(matrix, 1)).isTrue();
    Assertions.assertThat(demo.search(matrix, 7)).isTrue();
    Assertions.assertThat(demo.search(matrix, 23)).isTrue();
    Assertions.assertThat(demo.search(matrix, 50)).isTrue();
    Assertions.assertThat(demo.search(matrix, 2)).isFalse();
    Assertions.assertThat(demo.search(matrix, 51)).isFalse();
    Assertions.assertThat(demo.search(matrix, 35)).isFalse();
  }

  @Test
  public void test_search_0() {
    Demo demo = new Demo();

    int[][] matrix = {
        {1}};

    Assertions.assertThat(demo.search(matrix, 1)).isTrue();
  }
}
