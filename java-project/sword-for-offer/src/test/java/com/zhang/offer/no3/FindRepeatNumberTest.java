package com.zhang.offer.no3;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class FindRepeatNumberTest {

  private FindRepeatNumber finder;

  @Before
  public void setUp() {
    finder = new FindRepeatNumber();
  }

  @Test
  public void test_findRepeatNumber() {
    int num[] = {2, 3, 1, 0, 2, 5, 3};
    Assertions.assertThat(finder.findRepeatNumber(num)).isEqualTo(2);
  }

  @Test
  public void test_findRepeatNumber_1() {
    int num[] = {2, 3, 1, 0, 2, 5, 3};
    Assertions.assertThat(finder.findRepeatNumber_1(num)).isEqualTo(2);
  }

  @Test
  public void test_findRepeatNumber_2() {
    int num[] = {3, 1, 2, 3};
    Assertions.assertThat(finder.findRepeatNumber_1(num)).isEqualTo(3);
  }
}
