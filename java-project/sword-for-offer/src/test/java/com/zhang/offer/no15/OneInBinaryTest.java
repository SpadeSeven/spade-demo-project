package com.zhang.offer.no15;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class OneInBinaryTest {

  private OneInBinary oneInBinary;

  @Before
  public void setUp() {
    oneInBinary = new OneInBinary();
  }

  @Test
  public void test_HammingWeight() {
    Assertions.assertThat(oneInBinary.hammingWeight(11)).isEqualTo(3);
    Assertions.assertThat(oneInBinary.hammingWeight(128)).isEqualTo(1);
    Assertions.assertThat(oneInBinary.hammingWeight(-3)).isEqualTo(31);
    Assertions.assertThat(oneInBinary.hammingWeight(0b11111111111111111111111111111101))
        .isEqualTo(31);
  }
}
