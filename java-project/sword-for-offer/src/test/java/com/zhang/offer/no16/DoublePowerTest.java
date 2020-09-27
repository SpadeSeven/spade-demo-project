package com.zhang.offer.no16;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class DoublePowerTest {

  private DoublePower power;

  @Before
  public void setUp() {
    power = new DoublePower();
  }

  @Test
  public void test_MyPow() {
    Assertions.assertThat(power.myPow(2.00000, 10)).isEqualTo(1024.00000);
    Assertions.assertThat(power.myPow(2.10000, 3)).isEqualTo(9.26100);
    Assertions.assertThat(power.myPow(2.00000, -2)).isEqualTo(0.25000);
  }
}
