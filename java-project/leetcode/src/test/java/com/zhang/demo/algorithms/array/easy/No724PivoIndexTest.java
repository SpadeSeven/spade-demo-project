package com.zhang.demo.algorithms.array.easy;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class No724PivoIndexTest {

  private No724PivoIndex pivoIndex = new No724PivoIndex();

  @Test
  public void test_pivotIndex_0() {
    int[] arr = {1, 7, 3, 6, 5, 6};
    Assertions.assertThat(pivoIndex.pivotIndex(arr)).isEqualTo(3);
  }

  @Test
  public void test_pivotIndex_1() {
    int[] arr = {1, 2, 3};
    Assertions.assertThat(pivoIndex.pivotIndex(arr)).isEqualTo(-1);
  }

  @Test
  public void test_pivotIndex_2() {
    int[] arr = {-1, -1, -1, -1, -1, 0};
    Assertions.assertThat(pivoIndex.pivotIndex(arr)).isEqualTo(2);
  }
}
