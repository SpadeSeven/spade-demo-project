package com.zhang.offer.no20;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class IsNumberTest {

  private IsNumber isNumber;

  @Before
  public void setUp() {
    isNumber = new IsNumber();
  }

  @Test
  public void test_IsNumber() {
    Assertions.assertThat(isNumber.isNumber("+100")).isTrue();
    Assertions.assertThat(isNumber.isNumber("5e2")).isTrue();
    Assertions.assertThat(isNumber.isNumber("-123")).isTrue();
    Assertions.assertThat(isNumber.isNumber("3.1416")).isTrue();
    Assertions.assertThat(isNumber.isNumber("-1E-16")).isTrue();
    Assertions.assertThat(isNumber.isNumber("0123")).isTrue();
    Assertions.assertThat(isNumber.isNumber("12e")).isFalse();
    Assertions.assertThat(isNumber.isNumber("1a3.14")).isFalse();
    Assertions.assertThat(isNumber.isNumber("1.2.3")).isFalse();
    Assertions.assertThat(isNumber.isNumber("+-5")).isFalse();
    Assertions.assertThat(isNumber.isNumber("12e+5.4")).isFalse();
  }
}
