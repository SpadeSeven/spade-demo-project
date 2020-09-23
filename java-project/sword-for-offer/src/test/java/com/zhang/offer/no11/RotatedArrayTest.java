package com.zhang.offer.no11;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class RotatedArrayTest {

  private RotatedArray rotater;

  @Before
  public void setUp() {
    rotater = new RotatedArray();
  }

  @Test
  public void test_MinArray() {
    int[] nums_1 = {3, 4, 5, 1, 2};
    Assertions.assertThat(rotater.minArray(nums_1)).isEqualTo(1);

    int[] nums_2 = {2, 2, 2, 0, 1};
    Assertions.assertThat(rotater.minArray(nums_2)).isEqualTo(0);

    int[] nums_3 = {1};
    Assertions.assertThat(rotater.minArray(nums_3)).isEqualTo(1);

    int[] nums_4 = {1, 2};
    Assertions.assertThat(rotater.minArray(nums_4)).isEqualTo(1);

    int[] nums_5 = {2, 1};
    Assertions.assertThat(rotater.minArray(nums_5)).isEqualTo(1);
  }
}
