package com.zhang.offer.huawei.no13;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MainTest {

  private Main main = new Main();

  @Test
  public void test_Calcsub() {
    int[][] a1 = {
      {0, 3},
      {1, 3}
    };

    int[] a2 = {3, 3};
    Assertions.assertThat(main.calcsub(a1[0], a1[1])).isEqualTo(a2);
  }

  @Test
  public void test_Calcsub_2() {
    int[][] a1 = {
        {3, 5},
        {3, 6}
    };

    int[] a2 = {3, 5};
    Assertions.assertThat(main.calcsub(a1[0], a1[1])).isEqualTo(a2);
  }
}
