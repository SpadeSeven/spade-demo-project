package com.zhang.offer.no3;

import java.util.HashSet;
import java.util.Set;

/** 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。 */
public class FindRepeatNumber {

  /** 使用hash表处理 */
  public int findRepeatNumber(int[] nums) {

    Set<Integer> sets = new HashSet<>();

    for (int num : nums) {
      if (sets.contains(num)) {
        return num;
      }
      sets.add(num);
    }

    return 0;
  }

  /** 原地置换 */
  public int findRepeatNumber_1(int[] nums) {

    int temp;
    for (int index = 0; index < nums.length; index++) {
      // 当前下标放的不是自己
      while (nums[index] != index) {
        // 当前下表的值 =  值为下标的值
        if (nums[index] == nums[nums[index]]) {
          return nums[index];
        }
        temp = nums[index];
        nums[index] = nums[temp];
        nums[temp] = temp;
      }
    }
    return -1;
  }
}
