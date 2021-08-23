package com.zhang.demo.algorithms.dynamicprogramming.hard;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class SuperEggDropTest {

  private SuperEggDrop dropper;

  @Before
  public void setUp() {
    dropper = new SuperEggDrop();
  }

  @Test
  public void test_SuperEggDrop() {
    Assertions.assertThat(dropper.superEggDrop(1, 2)).isEqualTo(2);
    Assertions.assertThat(dropper.superEggDrop(2, 6)).isEqualTo(3);
    Assertions.assertThat(dropper.superEggDrop(3, 14)).isEqualTo(4);
  }
}
