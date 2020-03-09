package com.zhang.demo.algorithms.array.easy;

import org.junit.Assert;
import org.junit.Test;

/** Created by Administrator on 2019-03-06. */
public class RemoveDuplicatesTest {

  @Test
  public void test_1() {
    RemoveDuplicates remove = new RemoveDuplicates();
    int[] nums = {1, 1, 2};
    Assert.assertEquals(2, remove.removeDuplicates(nums));
  }

  @Test
  public void test_2() {
    RemoveDuplicates remove = new RemoveDuplicates();
    int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    Assert.assertEquals(5, remove.removeDuplicates(nums));
  }
}
