package com.zhang.offer.no42;

public class MaxSubArray {

  public int maxSubArray(int[] nums) {

    int max = nums[0];
    for (int index = 1; index < nums.length; index++) {
      if (nums[index - 1] >= 0) {
        nums[index] = nums[index - 1] + nums[index];
      }

      max = Math.max(max, nums[index]);
    }

    return max;
  }
}
