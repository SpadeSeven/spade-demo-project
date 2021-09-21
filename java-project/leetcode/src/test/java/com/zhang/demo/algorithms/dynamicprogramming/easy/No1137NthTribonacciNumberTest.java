package com.zhang.demo.algorithms.dynamicprogramming.easy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No1137NthTribonacciNumberTest {

  private No1137NthTribonacciNumber tribonacciNumber;

  @Before
  public void setUp() {
    tribonacciNumber = new No1137NthTribonacciNumber();
  }

  @Test
  public void test_Tribonacci() {
    Assertions.assertThat(tribonacciNumber.tribonacci(4)).isEqualTo(4);
    Assertions.assertThat(tribonacciNumber.tribonacci(25)).isEqualTo(1389537);
  }
}
