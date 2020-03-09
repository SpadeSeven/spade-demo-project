package com.zhang.demo.algorithms.array.easy;

/** Created by Administrator on 2019-03-06. */

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once
 * and return the new length.
 *
 * <p>Do not allocate extra space for another array, you must do this by modifying the input array
 * in-place with O(1) extra memory.
 */
public class RemoveDuplicates {

  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int i = 0;
    int j = 1;
    while (j <= nums.length - 1) {
      if (nums[i] != nums[j]) {
        nums[i + 1] = nums[j];
        i++;
      }
      j++;
    }
    return i + 1;
  }
}
