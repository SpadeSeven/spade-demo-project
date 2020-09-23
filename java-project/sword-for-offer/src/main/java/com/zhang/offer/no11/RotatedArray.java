package com.zhang.offer.no11;

public class RotatedArray {
  public int minArray(int[] numbers) {

    int begin = 0;
    int end = numbers.length - 1;
    int median = 0;
    while (begin <= end) {
      median = (end + begin) / 2;

      if (numbers[median] < numbers[end]) {
        end = median;
      } else if (numbers[median] > numbers[end]) {
        begin = median + 1;
      } else {
        end -= 1;
      }
    }

    return numbers[median];
  }
}
