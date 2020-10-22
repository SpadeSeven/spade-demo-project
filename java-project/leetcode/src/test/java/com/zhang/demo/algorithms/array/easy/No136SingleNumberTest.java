package com.zhang.demo.algorithms.array.easy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No136SingleNumberTest {

  private No136SingleNumber singleNumber;

  @Before
  public void setUp() {
    singleNumber = new No136SingleNumber();
  }

  @Test
  public void test_SingleNumber() {
    int[] arr_1 = {2, 2, 1};
    Assertions.assertThat(singleNumber.singleNumber(arr_1)).isEqualTo(1);

    int[] arr_2 = {4, 1, 2, 1, 2};
    Assertions.assertThat(singleNumber.singleNumber(arr_2)).isEqualTo(4);
  }
}
