package com.zhang.demo.algorithms.lists.hard;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No25ReverseKGroupTest {

  private No25ReverseKGroup reverser;

  @Before
  public void setUp() throws Exception {
    reverser = new No25ReverseKGroup();
  }

  @Test
  public void reverseKGroup_0() {
    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);

    ListNode rel = new ListNode(2);
    rel.next = new ListNode(1);
    rel.next.next = new ListNode(4);
    rel.next.next.next = new ListNode(3);
    rel.next.next.next.next = new ListNode(5);

    Assertions.assertThat(reverser.reverseKGroup(node, 2)).isEqualTo(rel);
  }

  @Test
  public void reverseKGroup_1() {
    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);

    ListNode rel = new ListNode(3);
    rel.next = new ListNode(2);
    rel.next.next = new ListNode(1);
    rel.next.next.next = new ListNode(4);
    rel.next.next.next.next = new ListNode(5);

    Assertions.assertThat(reverser.reverseKGroup(node, 3)).isEqualTo(rel);
  }

  @Test
  public void reverseKGroup_2() {
    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);

    ListNode rel = new ListNode(1);
    rel.next = new ListNode(2);
    rel.next.next = new ListNode(3);
    rel.next.next.next = new ListNode(4);
    rel.next.next.next.next = new ListNode(5);

    Assertions.assertThat(reverser.reverseKGroup(node, 1)).isEqualTo(rel);
  }

  @Test
  public void reverseKGroup_3() {
    ListNode node = new ListNode(1);
    ListNode rel = new ListNode(1);

    Assertions.assertThat(reverser.reverseKGroup(node, 1)).isEqualTo(rel);
  }
}
