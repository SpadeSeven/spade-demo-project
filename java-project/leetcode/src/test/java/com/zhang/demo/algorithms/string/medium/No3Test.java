package com.zhang.demo.algorithms.string.medium;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

public class No3Test {

  @Test
  public void test() {
    No3 no3 = new No3();
    Assertions.assertThat(no3.lengthOfLongestSubstring("abcabcbb")).isEqualTo(3);
    Assertions.assertThat(no3.lengthOfLongestSubstring("bbbbb")).isEqualTo(1);
    Assertions.assertThat(no3.lengthOfLongestSubstring("pwwkew")).isEqualTo(3);
    Assertions.assertThat(no3.lengthOfLongestSubstring("")).isEqualTo(0);
    Assertions.assertThat(no3.lengthOfLongestSubstring(" ")).isEqualTo(1);
    Assertions.assertThat(no3.lengthOfLongestSubstring("au")).isEqualTo(2);
    Assertions.assertThat(no3.lengthOfLongestSubstring("aab")).isEqualTo(2);
    Assertions.assertThat(no3.lengthOfLongestSubstring("abba")).isEqualTo(2);
  }

  @Ignore
  public void test_1() {
    String s = "\uD83D\uDC7F";
    System.out.println(s);
    System.out.println(s.length());
    System.out.println(s.charAt(0));
  }
}
