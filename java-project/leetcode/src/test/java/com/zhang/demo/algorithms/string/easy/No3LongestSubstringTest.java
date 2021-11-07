package com.zhang.demo.algorithms.string.easy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No3LongestSubstringTest {

  private No3LongestSubstring substring;

  @Before
  public void setUp() throws Exception {
    substring = new No3LongestSubstring();
  }

  @Test
  public void lengthOfLongestSubstring() {
    Assertions.assertThat(substring.lengthOfLongestSubstring("abcabcbb")).isEqualTo(3);
    Assertions.assertThat(substring.lengthOfLongestSubstring("bbbbb")).isEqualTo(1);
    Assertions.assertThat(substring.lengthOfLongestSubstring("pwwkew")).isEqualTo(3);
    Assertions.assertThat(substring.lengthOfLongestSubstring("")).isEqualTo(0);
    Assertions.assertThat(substring.lengthOfLongestSubstring("a")).isEqualTo(1);
    Assertions.assertThat(substring.lengthOfLongestSubstring("abcd")).isEqualTo(4);
    Assertions.assertThat(substring.lengthOfLongestSubstring("dadf")).isEqualTo(3);
  }
}
