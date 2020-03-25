package com.zhang.offer.no6;

import java.util.Stack;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

/** 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。 */
public class ReversePrint {

  public int[] reversePrint(ListNode head) {

    Stack<Integer> stack = new Stack<>();

    ListNode current = head;
    int size = 0;
    while (current != null) {
      stack.push(current.val);
      size++;
      current = current.next;
    }

    int[] result = new int[size];
    for (int index = 0; index < size; index++) {
      result[index] = stack.pop();
    }

    return result;
  }
}
