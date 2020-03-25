package com.zhang.offer.no6;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ReversePrintTest {

  private ReversePrint printer;

  @Before
  public void setUp() {
    printer = new ReversePrint();
  }

  @Test
  public void test_ReversePrint() {
    ListNode head = new ListNode(1);
    head.next = new ListNode(3);
    head.next.next = new ListNode(2);

    int[] expected = {2, 3, 1};
    Assertions.assertThat(printer.reversePrint(head)).isEqualTo(expected);

    int[] expected_1 = {};
    Assertions.assertThat(printer.reversePrint(null)).isEqualTo(expected_1);
  }
}
