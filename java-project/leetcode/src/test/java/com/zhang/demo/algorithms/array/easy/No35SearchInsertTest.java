package com.zhang.demo.algorithms.array.easy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No35SearchInsertTest {
  private No35SearchInsert searcher;

  @Before
  public void setUp() {
    searcher = new No35SearchInsert();
  }

  @Test
  public void test_searchInsert_0() {
    int[] arr = {1, 3, 5, 6};
    Assertions.assertThat(searcher.searchInsert(arr, 5)).isEqualTo(2);
    Assertions.assertThat(searcher.searchInsert(arr, 2)).isEqualTo(1);
    Assertions.assertThat(searcher.searchInsert(arr, 7)).isEqualTo(4);
    Assertions.assertThat(searcher.searchInsert(arr, 0)).isEqualTo(0);
  }
}
