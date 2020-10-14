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

    // 处理头节点
    if (head.val == val) {
      return head.next;
    }

    ListNode pre = head;
    ListNode cur = head.next;
    while (cur != null) {
      if (cur.val == val) {
        pre.next = cur.next;
        break;
      } else {
        pre = cur;
        cur = cur.next;
      }
    }

    return head;
  }
}
