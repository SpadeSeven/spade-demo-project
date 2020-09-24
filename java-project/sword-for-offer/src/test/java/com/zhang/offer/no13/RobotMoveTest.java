package com.zhang.offer.no13;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class RobotMoveTest {

  private RobotMove move;

  @Before
  public void setUp() {
    move = new RobotMove();
  }

  @Test
  public void test_movingCount_0() {
    Assertions.assertThat(move.movingCount(2, 3, 1)).isEqualTo(3);
    Assertions.assertThat(move.movingCount(3, 1, 0)).isEqualTo(1);
  }

  @Test
  public void test_ValidNum() {
    Assertions.assertThat(move.validNum(35, 37,18)).isTrue();
    Assertions.assertThat(move.validNum(35, 38,18)).isFalse();
  }

  @Test
  public void test_GetNumSum() {
    Assertions.assertThat(move.getNumSum(1)).isEqualTo(1);
    Assertions.assertThat(move.getNumSum(10)).isEqualTo(1);
    Assertions.assertThat(move.getNumSum(123)).isEqualTo(6);
  }
}
