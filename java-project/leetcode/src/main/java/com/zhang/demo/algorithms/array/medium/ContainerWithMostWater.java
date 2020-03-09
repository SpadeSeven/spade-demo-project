package com.zhang.demo.algorithms.array.medium;

/**
 * @author spade
 * @time 2019-01-05
 */

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i,
 * ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains
 * the most water.
 *
 * <p>Example: Input: [1,8,6,2,5,4,8,3,7] Output: 49
 */
public class ContainerWithMostWater {

  public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int max_area = 0;
    while (left < right) {
      int current_area = (right - left) * Math.min(height[left], height[right]);
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
      max_area = Math.max(max_area, current_area);
    }
    return max_area;
  }
}
