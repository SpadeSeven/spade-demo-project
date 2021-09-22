package com.zhang.demo.algorithms.dynamicprogramming.easy.No70;

import com.zhang.demo.algorithms.dynamicprogramming.easy.No70ClimbingStairs;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No70ClimbingStairsTest {

  private No70ClimbingStairs climbing;

  @Before
  public void setUp() {
    climbing = new No70ClimbingStairs();
  }

  @Test
  public void test_ClimbStairs() {
    Assertions.assertThat(climbing.climbStairs(2)).isEqualTo(2);
    Assertions.assertThat(climbing.climbStairs(3)).isEqualTo(3);
  }
}
