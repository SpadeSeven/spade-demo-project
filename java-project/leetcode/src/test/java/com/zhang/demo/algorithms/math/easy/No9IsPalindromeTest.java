package com.zhang.demo.algorithms.math.easy;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class No9IsPalindromeTest {

  @Test
  public void test_IsPalindrome() {
    No9IsPalindrome isPalindrome = new No9IsPalindrome();

    Assertions.assertThat(isPalindrome.isPalindrome(121)).isTrue();
    Assertions.assertThat(isPalindrome.isPalindrome(1)).isTrue();
    Assertions.assertThat(isPalindrome.isPalindrome(10)).isFalse();
    Assertions.assertThat(isPalindrome.isPalindrome(-121)).isFalse();
    Assertions.assertThat(isPalindrome.isPalindrome(-1221)).isFalse();
  }
}
