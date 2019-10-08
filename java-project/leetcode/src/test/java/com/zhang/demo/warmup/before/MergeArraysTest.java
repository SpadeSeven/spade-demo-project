package com.zhang.demo.warmup.before;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class MergeArraysTest {

  private MergeArrays merger;

  @Before
  public void before() {
    merger = new MergeArrays();
  }

  @Test
  public void test() {
    int[] nums1 = {1, 2, 3, 0, 0, 0};
    int[] nums2 = {2, 5, 6};
    int m = 3;
    int n = 3;

    merger.merge(nums1, m, nums2, n);

    int[] expected = {1, 2, 2, 3, 5, 6};
    Assertions.assertThat(nums1).isEqualTo(expected);
  }

  @Test
  public void test_1() {
    int[] nums1 = {5, 6, 7, 0, 0, 0};
    int[] nums2 = {1, 2, 3};
    int m = 3;
    int n = 3;

    merger.merge(nums1, m, nums2, n);

    int[] expected = {1, 2, 3, 5, 6, 7};
    Assertions.assertThat(nums1).isEqualTo(expected);
  }

  @Test
  public void test_2() {
    int[] nums1 = {1, 2, 3, 0, 0, 0};
    int[] nums2 = {5, 6, 7};
    int m = 3;
    int n = 3;

    merger.merge(nums1, m, nums2, n);

    int[] expected = {1, 2, 3, 5, 6, 7};
    Assertions.assertThat(nums1).isEqualTo(expected);
  }

  @Test
  public void test_3() {
    int[] nums1 = {0};
    int[] nums2 = {1};
    int m = 0;
    int n = 1;

    merger.merge(nums1, m, nums2, n);

    int[] expected = {1};
    Assertions.assertThat(nums1).isEqualTo(expected);
  }
}
