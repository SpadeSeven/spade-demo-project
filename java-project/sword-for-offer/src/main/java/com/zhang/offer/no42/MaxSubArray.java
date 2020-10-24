package com.zhang.offer.no42;

import java.util.Arrays;

public class MaxSubArray {

  public int maxSubArray(int[] nums) {

    int max = Integer.MIN_VALUE;

    for (int i = 1; i <= nums.length; i++) {
      for (int j = 0; j <= nums.length - i; j++) {
        max = Math.max(max, sumOfArray(Arrays.copyOfRange(nums, j, j + i)));
      }
    }
    return max;
  }

  int sumOfArray(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    return sum;
  }
}
