package com.zhang.demo.algorithms.lists;

/**
 * 2:https://leetcode-cn.com/problems/add-two-numbers/
 *
 * <p>给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的， 并且它们的每个节点只能存储 一位 数字。
 *
 * <p>如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * <p>您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * <p>输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 */
public class No2 {

  public static void main(String[] args) {
    ListNode node_1 = new ListNode(2);
    node_1.next = new ListNode(4);
    node_1.next.next = new ListNode(3);

    ListNode node_2 = new ListNode(5);
    node_2.next = new ListNode(6);
    node_2.next.next = new ListNode(4);

    No2 no2 = new No2();
    no2.addTwoNums(node_1, node_2);
  }

  public ListNode addTwoNums(ListNode node_1, ListNode node_2) {

    ListNode dummyHead = new ListNode(0);

    ListNode p = node_1;
    ListNode q = node_2;

    ListNode current = dummyHead;

    // 进位
    int carry = 0;

    while (p != null || q != null) {

      int x = (p != null) ? p.val : 0;
      int y = (q != null) ? q.val : 0;
      int sum = x + y + carry;

      carry = sum / 10;
      current.next = new ListNode(sum % 10);

      current = current.next;

      if (p.next != null) {
        p = p.next;
      }

      if (q.next != null) {
        q = q.next;
      }
    }

    if (carry > 0) {
      current.next = new ListNode(carry);
    }

    return dummyHead.next;
  }
}

class ListNode {

  int val;

  ListNode next;

  ListNode(int x) {
    val = x;
  }
}
