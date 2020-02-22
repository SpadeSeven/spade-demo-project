package com.zhang.demo.warmup.simulate;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class SumOfTwoIntegersTest {

  private SumOfTwoIntegers summer;

  @Before
  public void before() {
    summer = new SumOfTwoIntegers();
  }

  @Test
  public void test() {

    Assertions.assertThat(summer.getSum(1, 2)).isEqualTo(3);
    Assertions.assertThat(summer.getSum(2, 2)).isEqualTo(4);

    // Assertions.assertThat(summer.getSum(-2, 3)).isEqualTo(1);

  }
}
