package com.zhang.demo.warmup.before;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class MajorityElementTest {

  private MajorityElement majority;

  @Before
  public void before() {
    majority = new MajorityElement();
  }

  @Test
  public void test_sort() {
    int[] arr_1 = {3, 2, 3};
    Assertions.assertThat(majority.majorityElement_sort(arr_1)).isEqualTo(3);

    int[] arr_2 = {2, 2, 1, 1, 1, 2, 2};
    Assertions.assertThat(majority.majorityElement_sort(arr_2)).isEqualTo(2);
  }

  @Test
  public void test_boyerMoore() {
    int[] arr_1 = {3, 2, 3};
    Assertions.assertThat(majority.majorityElement_boyerMoore(arr_1)).isEqualTo(3);

    int[] arr_2 = {2, 2, 1, 1, 1, 2, 2};
    Assertions.assertThat(majority.majorityElement_boyerMoore(arr_2)).isEqualTo(2);
  }
}
