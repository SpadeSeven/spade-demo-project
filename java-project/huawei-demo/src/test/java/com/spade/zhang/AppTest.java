package com.spade.zhang;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AppTest {

  @Test
  public void test_Sum() {

    App app = new App();

    ListNode node1 = new ListNode(7);
    node1.next = new ListNode(1);
    node1.next.next = new ListNode(6);

    ListNode node2 = new ListNode(5);
    node2.next = new ListNode(9);
    node2.next.next = new ListNode(2);

    ListNode expected = new ListNode(2);
    expected.next = new ListNode(1);
    expected.next.next = new ListNode(9);

    Assertions.assertThat(app.sum(node1, node2)).isEqualTo(node1);

  }
}
