package com.zhang.offer.no42;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class MaxSubArrayTest {

  private MaxSubArray max;

  @Before
  public void setUp() {
    max = new MaxSubArray();
  }

  @Test
  public void test_maxSubArray_0() {
    int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    Assertions.assertThat(max.maxSubArray(arr)).isEqualTo(6);
  }

  @Test
  public void test_maxSubArray_1() {
    int[] arr = {1};
    Assertions.assertThat(max.maxSubArray(arr)).isEqualTo(1);
  }
}
