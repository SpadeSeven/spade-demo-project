package com.zhang.demo.algorithms.array.medium;

/**
 * @author spade
 * @time 2019-01-05
 */

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i,
 * ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains
 * the most water. <p> Example: Input: [1,8,6,2,5,4,8,3,7] Output: 49
 */
public class ContainerWithMostWater {

  public int maxArea(int[] height) {
    int current_height = 0;
    int area_size = 0;
    int j = 0;
    for (int i = 0; i < height.length - 1; i++) {
      if (height[i] < height[j]) {
        current_height = height[j];
      }
      current_height = height[i];
      area_size = current_height * (j - i + 1);
    }
    return area_size;
  }

}
