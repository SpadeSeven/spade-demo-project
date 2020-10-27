package com.zhang.demo.algorithms.string.medium;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class No6ZConvertTest {

  @Test
  public void test_Convert() {
    No6ZConvert convert = new No6ZConvert();
    Assertions.assertThat(convert.convert("LEETCODEISHIRING", 3)).isEqualTo("LCIRETOESIIGEDHN");
    Assertions.assertThat(convert.convert("LEETCODEISHIRING", 4)).isEqualTo("LDREOEIIECIHNTSG");
    Assertions.assertThat(convert.convert("A", 1)).isEqualTo("A");
  }

  @Test
  public void test_CalcCols() {
    No6ZConvert convert = new No6ZConvert();

    Assertions.assertThat(convert.calcCols(1, 3)).isEqualTo(1);
    Assertions.assertThat(convert.calcCols(2, 3)).isEqualTo(1);
    Assertions.assertThat(convert.calcCols(3, 3)).isEqualTo(1);
    Assertions.assertThat(convert.calcCols(4, 3)).isEqualTo(2);
    Assertions.assertThat(convert.calcCols(5, 3)).isEqualTo(3);

    Assertions.assertThat(convert.calcCols(16, 3)).isEqualTo(8);
    Assertions.assertThat(convert.calcCols(16, 4)).isEqualTo(7);

    Assertions.assertThat(convert.calcCols(5, 4)).isEqualTo(2);
  }

  @Test
  public void test_CalcRow() {
    No6ZConvert convert = new No6ZConvert();
    Assertions.assertThat(convert.calcRow(1, 4)).isEqualTo(1);
    Assertions.assertThat(convert.calcRow(2, 4)).isEqualTo(2);
    Assertions.assertThat(convert.calcRow(3, 4)).isEqualTo(3);
    Assertions.assertThat(convert.calcRow(4, 4)).isEqualTo(4);
    Assertions.assertThat(convert.calcRow(5, 4)).isEqualTo(3);
    Assertions.assertThat(convert.calcRow(6, 4)).isEqualTo(2);
    Assertions.assertThat(convert.calcRow(7, 4)).isEqualTo(1);

    Assertions.assertThat(convert.calcRow(8, 4)).isEqualTo(2);
  }
}
