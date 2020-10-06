package com.zhang.offer.huawei.no12;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MainTest {

  private Main main = new Main();

  @Test
  public void test_SumOfByte() {
    Assertions.assertThat(main.sumOfByte(256)).isEqualTo(1);
    Assertions.assertThat(main.sumOfByte(257)).isEqualTo(2);
    Assertions.assertThat(main.sumOfByte(258)).isEqualTo(3);
    Assertions.assertThat(main.sumOfByte(259)).isEqualTo(4);
    Assertions.assertThat(main.sumOfByte(260)).isEqualTo(5);
    Assertions.assertThat(main.sumOfByte(261)).isEqualTo(6);
    Assertions.assertThat(main.sumOfByte(262)).isEqualTo(7);
    Assertions.assertThat(main.sumOfByte(263)).isEqualTo(8);
    Assertions.assertThat(main.sumOfByte(264)).isEqualTo(9);
    Assertions.assertThat(main.sumOfByte(265)).isEqualTo(10);
    Assertions.assertThat(main.sumOfByte(266));
    Assertions.assertThat(main.sumOfByte(Integer.MAX_VALUE));
    Assertions.assertThat(main.sumOfByte(Integer.MIN_VALUE));
  }
}
