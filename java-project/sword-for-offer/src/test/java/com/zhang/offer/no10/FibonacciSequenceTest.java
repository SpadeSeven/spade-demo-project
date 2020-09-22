package com.zhang.offer.no10;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class FibonacciSequenceTest {

  private FibonacciSequence fibonacci;

  @Before
  public void setUp() {
    fibonacci = new FibonacciSequence();
  }

  @Test
  public void test_Fib() {
    Assertions.assertThat(fibonacci.fib(0)).isEqualTo(0);
    Assertions.assertThat(fibonacci.fib(1)).isEqualTo(1);
    Assertions.assertThat(fibonacci.fib(2)).isEqualTo(1);
    Assertions.assertThat(fibonacci.fib(5)).isEqualTo(5);
    Assertions.assertThat(fibonacci.fib(44)).isEqualTo(701408733);
  }
}
