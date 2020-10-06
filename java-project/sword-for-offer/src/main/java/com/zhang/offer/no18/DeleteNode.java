package com.zhang.offer.no18;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

public class DeleteNode {
  public ListNode deleteNode(ListNode head, int val) {

    if (head.val == val) {
      return head.next;
    }

    ListNode res = head;
    ListNode tmp = head;
    while (head.next != null) {
      if (tmp.next.val == val) {
        res.next = tmp.next.next;
        return res;
      } else {
        res.next = new ListNode(tmp.next.val);
        tmp = tmp.next;
      }
    }

    return res;
  }
}
