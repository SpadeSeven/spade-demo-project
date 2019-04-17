package com.zhang.demo.sort;

/**
 * Created by Administrator on 2019-04-16.
 */
public class BubbleSort extends Sorts {

  @Override
  public void sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          swap(arr, j, j + 1);
        }
      }
    }
  }

  @Override
  public void sort_optimization(int[] arr) {
    boolean flag = true;
    for (int i = 0; i < arr.length; i++) {
      flag = false;
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          swap(arr, j, j + 1);
          flag = true;
        }
      }
      if (!flag) {
        return;
      }
    }
  }
}
