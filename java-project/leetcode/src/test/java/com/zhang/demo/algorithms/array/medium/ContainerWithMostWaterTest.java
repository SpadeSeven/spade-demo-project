package com.zhang.demo.algorithms.array.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author spade
 * @time 2019-01-05
 */
public class ContainerWithMostWaterTest {

  @Test
  public void test_ContainerWithMostWater() {
    int[] input = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
    int result = containerWithMostWater.maxArea(input);

    Assert.assertEquals(49, result);
  }

}
