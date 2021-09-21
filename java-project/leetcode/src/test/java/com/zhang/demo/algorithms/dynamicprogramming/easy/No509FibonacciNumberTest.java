package com.zhang.demo.algorithms.dynamicprogramming.easy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No509FibonacciNumberTest {

  private No509FibonacciNumber fibonacci;

  @Before
  public void setUp() {
    fibonacci = new No509FibonacciNumber();
  }

  @Test
  public void test_Fib() {
    Assertions.assertThat(fibonacci.fib(2)).isEqualTo(1);
    Assertions.assertThat(fibonacci.fib(3)).isEqualTo(2);
    Assertions.assertThat(fibonacci.fib(4)).isEqualTo(3);
  }
}
