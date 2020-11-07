package com.zhang.demo.algorithms.string.find;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BruteForceTest {

  private BruteForce finder;

  @Before
  public void setUp() {
    finder = new BruteForce();
  }

  @Test
  public void test_find_empty() {
    Assertions.assertThat(finder.find("", "")).isEqualTo(0);
    Assertions.assertThat(finder.find(null, "")).isEqualTo(-1);
    Assertions.assertThat(finder.find("", null)).isEqualTo(-1);
    Assertions.assertThat(finder.find(null, null)).isEqualTo(0);
  }

  @Test
  public void test_find_short() {
    Assertions.assertThat(finder.find("hello", "h")).isEqualTo(0);
    Assertions.assertThat(finder.find("hello", "he")).isEqualTo(0);
    Assertions.assertThat(finder.find("hello", "ll")).isEqualTo(2);
    Assertions.assertThat(finder.find("hello", "w")).isEqualTo(-1);
  }
}
