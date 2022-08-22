package com.zhang.demo.algorithms.lists.easy;

import com.zhang.demo.algorithms.lists.ListNode;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class No206ReverseListTest {

  private No206ReverseList reverser;

  @Before
  public void setUp() {
    reverser = new No206ReverseList();
  }

  @Test
  public void test_ReverseList_0() {
    ListNode head = new ListNode();
    head.val = 1;
    head.next = new ListNode();
    head.next.val = 2;
    head.next.next = new ListNode();
    head.next.next.val = 3;
    head.next.next.next = new ListNode();
    head.next.next.next.val = 4;
    head.next.next.next.next = new ListNode();
    head.next.next.next.next.val = 5;

    ListNode rel = new ListNode();
    rel.val = 5;
    rel.next = new ListNode();
    rel.next.val = 4;
    rel.next.next = new ListNode();
    rel.next.next.val = 3;
    rel.next.next.next = new ListNode();
    rel.next.next.next.val = 2;
    rel.next.next.next.next = new ListNode();
    rel.next.next.next.next.val = 1;

    Assertions.assertThat(reverser.reverseList(head)).isEqualTo(rel);
  }

  @Test
  public void test_ReverseList_1() {
    ListNode head = new ListNode();
    head.val = 1;
    head.next = new ListNode();
    head.next.val = 2;

    ListNode rel = new ListNode();
    rel.val = 2;
    rel.next = new ListNode();
    rel.next.val = 1;

    Assertions.assertThat(reverser.reverseList(head)).isEqualTo(rel);
  }

  @Test
  public void test_ReverseList_2() {
    ListNode head = new ListNode();

    ListNode rel = new ListNode();

    Assertions.assertThat(reverser.reverseList(head)).isEqualTo(rel);
  }
}
