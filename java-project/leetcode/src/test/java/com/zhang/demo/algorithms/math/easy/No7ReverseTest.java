package com.zhang.demo.algorithms.math.easy;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class No7ReverseTest {

  @Test
  public void test_Reverse() {
    No7Reverse reverser = new No7Reverse();

    Assertions.assertThat(reverser.reverse(123)).isEqualTo(321);
    Assertions.assertThat(reverser.reverse(-123)).isEqualTo(-321);
    Assertions.assertThat(reverser.reverse(120)).isEqualTo(21);
    Assertions.assertThat(reverser.reverse(1)).isEqualTo(1);
    Assertions.assertThat(reverser.reverse(-1)).isEqualTo(-1);
    Assertions.assertThat(reverser.reverse(0)).isEqualTo(0);
    Assertions.assertThat(reverser.reverse(1534236469)).isEqualTo(0);
  }
}
