package com.zhang.demo.algorithms.binarysearch;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {

  private BinarySearch searcher;

  @Before
  public void setUp() {
    searcher = new BinarySearch();
  }

  @Test
  public void test_search_1() {
    int[] arr = {-1, 0, 3, 5, 9, 12};
    Assertions.assertThat(searcher.search(arr, 9)).isEqualTo(4);
    Assertions.assertThat(searcher.search(arr, 2)).isEqualTo(-1);
  }

  @Test
  public void test_search_2() {
    int[] arr = {-1};
    Assertions.assertThat(searcher.search(arr, -1)).isEqualTo(0);
    Assertions.assertThat(searcher.search(arr, 2)).isEqualTo(-1);
  }
}
