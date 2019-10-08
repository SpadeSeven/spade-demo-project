package com.zhang.demo.warmup.before;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class SingleNumberTest {

  private SingleNumber single;

  @Before
  public void before() {
    single = new SingleNumber();
  }

  @Test
  public void test() {
    int[] arr_1 = {2, 2, 1};
    Assertions.assertThat(single.singleNumber(arr_1)).isEqualTo(1);

    int[] arr_2 = {4, 1, 2, 1, 2};
    Assertions.assertThat(single.singleNumber(arr_2)).isEqualTo(4);
  }
}
