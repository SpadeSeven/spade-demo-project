package com.zhang.demo.algorithms.string.easy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No14LongestCommonPrefixTest {

  private No14LongestCommonPrefix prefix;

  @Before
  public void setUp() {
    prefix = new No14LongestCommonPrefix();
  }

  @Test
  public void test_longestCommonPrefix_0() {
    String[] arr = {"flower", "flow", "flight"};
    Assertions.assertThat(prefix.longestCommonPrefix(arr)).isEqualTo("fl");
  }

  @Test
  public void test_longestCommonPrefix_1() {
    String[] arr = {"dog", "racecar", "car"};
    Assertions.assertThat(prefix.longestCommonPrefix(arr)).isEqualTo("");
  }

  @Test
  public void test_longestCommonPrefix_2() {
    String[] arr = {};
    Assertions.assertThat(prefix.longestCommonPrefix(arr)).isEqualTo("");
  }
}
