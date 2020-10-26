package com.zhang.demo.algorithms.array.easy;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class No66PlusOneTest {

  @Test
  public void test_plusOne_0() {
    No66PlusOne plusOne = new No66PlusOne();

    int[] arr_1 = {1, 2, 3};
    int[] arr_2 = {1, 2, 4};

    Assertions.assertThat(plusOne.plusOne(arr_1)).isEqualTo(arr_2);
  }

  @Test
  public void test_plusOne_1() {
    No66PlusOne plusOne = new No66PlusOne();

    int[] arr_1 = {9, 9, 9};
    int[] arr_2 = {1, 0, 0, 0};

    Assertions.assertThat(plusOne.plusOne(arr_1)).isEqualTo(arr_2);
  }
}
