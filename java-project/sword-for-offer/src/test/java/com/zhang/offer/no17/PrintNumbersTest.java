package com.zhang.offer.no17;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class PrintNumbersTest {

  private PrintNumbers printer;

  @Before
  public void setUp() {
    printer = new PrintNumbers();
  }

  @Test
  public void test_PrintNumbers() {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    Assertions.assertThat(printer.printNumbers(1)).isEqualTo(arr);
  }
}
