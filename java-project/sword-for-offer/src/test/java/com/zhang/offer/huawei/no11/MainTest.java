package com.zhang.offer.huawei.no11;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MainTest {

  private Main main = new Main();

  @Test
  public void test_maxSum() {
    int[][] arr = {
      {-3, 5, -1, 5},
      {2, 4, -2, 4},
      {-1, 3, -1, 3}
    };

    Assertions.assertThat(main.maxSum(arr)).isEqualTo(20);
  }

  @Test
  public void test_maxSubArr() {
    int[][] arr = {
      {-3, 5, -1, 5},
      {2, 4, -2, 4},
      {-1, 3, -1, 3}
    };

    Assertions.assertThat(main.maxSubArr(arr, 0, 1)).isEqualTo(20);
  }

  @Test
  public void test_SumOfSubArr() {
    int[][] arr = {
      {-3, 5, -1, 5},
      {2, 4, -2, 4},
      {-1, 3, -1, 3}
    };

    Assertions.assertThat(main.sumOfSubArr(arr, 0, 1, 0, 1)).isEqualTo(5);
    Assertions.assertThat(main.sumOfSubArr(arr, 0, 1, 0, 2)).isEqualTo(4);
    Assertions.assertThat(main.sumOfSubArr(arr, 0, 1, 2, 3)).isEqualTo(20);
  }
}
