package com.zhang.demo.warmup.string;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class IsPalindromeTest {

  private IsPalindrome isPalindrome;

  @Before
  public void before() {
    isPalindrome = new IsPalindrome();
  }

  @Test
  public void test() {
    String input_1 = "A man, a plan, a canal: Panama";
    Assertions.assertThat(isPalindrome.isPalindrome(input_1)).isTrue();

    String input_2 = "race a car";
    Assertions.assertThat(isPalindrome.isPalindrome(input_2)).isFalse();

    String input_3 = "";
    Assertions.assertThat(isPalindrome.isPalindrome(input_3)).isTrue();
  }
}
