package com.zhang.demo.algorithms.array.easy;

public class No724PivoIndex {

  public int pivotIndex(int[] nums) {

    if (nums == null || nums.length < 3) {
      return -1;
    }

    int sum = 0;
    for (int num : nums) {
      sum += num;
    }

    int sum_left = 0;
    for (int index = 0; index < nums.length; index++) {
      if (sum_left == sum - nums[index] - sum_left) {
        return index;
      }

      sum_left += nums[index];
    }

    return -1;
  }
}
