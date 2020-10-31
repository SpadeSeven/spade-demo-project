package com.zhang.demo.algorithms.array.easy;

public class No35SearchInsert {

  public int searchInsert(int[] nums, int target) {

    for (int index = 0; index < nums.length; index++) {
      if (nums[index] == target) {
        return index;
      } else if (nums[index] > target && index == 0) {
        return 0;
      } else if (nums[index] > target) {
        return index;
      }
    }

    return nums.length;
  }
}
