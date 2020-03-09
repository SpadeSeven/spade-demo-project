package com.zhang.demo.algorithms.array.easy;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author spade
 * @time 2019-01-05
 */
public class TwoSumTest {

  @Test
  public void test_twosum() {
    int[] nums = {2, 7, 11, 15};
    int target = 9;

    TwoSum twoSum = new TwoSum();
    int[] result = twoSum.twoSum(nums, target);

    int[] expect = {0, 1};
    Assert.assertArrayEquals(expect, result);
  }
}
