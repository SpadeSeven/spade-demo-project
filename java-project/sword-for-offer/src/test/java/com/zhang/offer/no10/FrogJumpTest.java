package com.zhang.offer.no10;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class FrogJumpTest {

  private FrogJump jumper;

  @Before
  public void setUp() {
    jumper = new FrogJump();
  }

  @Test
  public void test_NumWays() {
    Assertions.assertThat(jumper.numWays(0)).isEqualTo(1);
    Assertions.assertThat(jumper.numWays(1)).isEqualTo(1);
    Assertions.assertThat(jumper.numWays(2)).isEqualTo(2);
    Assertions.assertThat(jumper.numWays(3)).isEqualTo(3);
    Assertions.assertThat(jumper.numWays(4)).isEqualTo(5);
    Assertions.assertThat(jumper.numWays(5)).isEqualTo(8);
    Assertions.assertThat(jumper.numWays(6)).isEqualTo(13);
    Assertions.assertThat(jumper.numWays(7)).isEqualTo(21);
  }
}
