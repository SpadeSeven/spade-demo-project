package com.zhang.demo.algorithms.dynamicprogramming.easy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No746MinCostClimbingStairsTest {

  private No746MinCostClimbingStairs minCostClimbingStairs;

  @Before
  public void setUp() {
    minCostClimbingStairs = new No746MinCostClimbingStairs();
  }

  @Test
  public void test_MinCostClimbingStairs_0() {
    int[] cost = {10, 15, 20};
    Assertions.assertThat(minCostClimbingStairs.minCostClimbingStairs(cost)).isEqualTo(15);
  }

  @Test
  public void test_MinCostClimbingStairs_1() {
    int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
    Assertions.assertThat(minCostClimbingStairs.minCostClimbingStairs(cost)).isEqualTo(6);
  }
}
