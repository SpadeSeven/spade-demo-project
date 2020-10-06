package com.zhang.offer.no18;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class DeleteNodeTest {

  private DeleteNode deleter;

  @Before
  public void setUp() {
    deleter = new DeleteNode();
  }

  @Test
  public void test_deleteNode_1() {
    ListNode head = new ListNode(4);
    head.next = new ListNode(5);
    head.next.next = new ListNode(1);
    head.next.next.next = new ListNode(9);

    ListNode res = deleter.deleteNode(head, 5);
    Assertions.assertThat(res.val).isEqualTo(4);
    Assertions.assertThat(res.next.val).isEqualTo(1);
    Assertions.assertThat(res.next.next.val).isEqualTo(9);
    Assertions.assertThat(res.next.next.next).isEqualTo(null);
  }

  @Test
  public void test_deleteNode_2() {
    ListNode head = new ListNode(4);
    head.next = new ListNode(5);
    head.next.next = new ListNode(1);
    head.next.next.next = new ListNode(9);

    ListNode res = deleter.deleteNode(head, 9);
    Assertions.assertThat(res.val).isEqualTo(4);
    Assertions.assertThat(res.next.val).isEqualTo(5);
    Assertions.assertThat(res.next.next.val).isEqualTo(1);
    Assertions.assertThat(res.next.next.next).isEqualTo(null);
  }

  @Test
  public void test_deleteNode_3() {
    ListNode head = new ListNode(4);
    head.next = new ListNode(5);
    head.next.next = new ListNode(1);
    head.next.next.next = new ListNode(9);

    ListNode res = deleter.deleteNode(head, 4);
    Assertions.assertThat(res.val).isEqualTo(5);
    Assertions.assertThat(res.next.val).isEqualTo(1);
    Assertions.assertThat(res.next.next.val).isEqualTo(9);
    Assertions.assertThat(res.next.next.next).isEqualTo(null);
  }
}
