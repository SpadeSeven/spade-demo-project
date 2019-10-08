package com.zhang.demo.warmup.before;

/**
 * 只出现一次的数字
 *
 * <p>给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * <p>示例 1: 输入: [2,2,1] 输出: 1
 *
 * <p>示例 2:输入: [4,1,2,1,2] 输出: 4
 */
public class SingleNumber {

  public int singleNumber(int[] nums) {

    int result = 0;
    for (int num : nums) {
      // 使用位运算
      result = num ^ result;
    }

    return result;
  }
}
