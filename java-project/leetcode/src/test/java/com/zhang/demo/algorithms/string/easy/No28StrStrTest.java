package com.zhang.demo.algorithms.string.easy;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No28StrStrTest {

  private No28StrStr strStr;

  @Before
  public void setUp() {
    strStr = new No28StrStr();
  }

  @Test
  public void test_StrStr() {
    Assertions.assertThat(strStr.strStr("hello", "ll")).isEqualTo(2);
    Assertions.assertThat(strStr.strStr("aaaaa", "bba")).isEqualTo(-1);
  }
}
