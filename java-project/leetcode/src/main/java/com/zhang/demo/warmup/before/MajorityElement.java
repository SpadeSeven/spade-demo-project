package com.zhang.demo.warmup.before;

import java.util.Arrays;

/**
 * 求众数
 *
 * <p>给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * <p>你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * <p>示例 1: 输入: [3,2,3] 输出: 3
 *
 * <p>示例 2: 输入: [2,2,1,1,1,2,2] 输出: 2
 */
public class MajorityElement {

  /**
   * 排序法
   *
   * @param nums
   * @return
   */
  public int majorityElement_sort(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length / 2];
  }

  /**
   * Boyer-Moore 投票算法
   *
   * @param nums
   * @return
   */
  public int majorityElement_boyerMoore(int[] nums) {

    //  候选人
    int candidate = nums[0];
    int count = 0;

    for (int num : nums) {
      if (count == 0) {
        candidate = num;
      }
      if (candidate == num) {
        count += 1;
      } else {
        count -= 1;
      }
    }

    return candidate;
  }
}
