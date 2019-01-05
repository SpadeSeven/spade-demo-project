package com.zhang.demo.algorithms.array.easy;

/**
 * @author spade
 * @time 2019-01-05
 */

import com.google.common.collect.Maps;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific
 * target. You may assume that each input would have exactly one solution, and you may not use the
 * same element twice. Example: Given nums = [2, 7, 11, 15], target = 9, Because nums[0] + nums[1] =
 * 2 + 7 = 9, return [0, 1].
 */
public class TwoSum {

  public int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];

    Map<Integer, Integer> map = Maps.newHashMap();
    for (int i = 0; i < nums.length; i++) {
      int expect = target - nums[i];
      if (map.containsKey(expect)) {
        if (i < map.get(expect)) {
          result[0] = i;
          result[1] = map.get(expect);
        } else {
          result[0] = map.get(expect);
          result[1] = i;
        }
        return result;
      } else {
        map.put(nums[i], i);
      }
    }

    return result;
  }

}
