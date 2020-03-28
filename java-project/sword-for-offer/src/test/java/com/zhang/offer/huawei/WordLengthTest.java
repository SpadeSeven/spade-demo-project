package com.zhang.offer.huawei;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class WordLengthTest {

  private WordLength wordLength;

  @Before
  public void setUp() {
    wordLength = new WordLength();
  }

  @Test
  public void test_WordLength() {
    Assertions.assertThat(wordLength.wordLength("hello world")).isEqualTo(5);
  }
}
