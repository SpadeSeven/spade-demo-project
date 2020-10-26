package com.spade.zhang;


class ListNode {

  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
  }
}

public class App {
  ListNode sum(ListNode a, ListNode b) {
    ListNode result = null;
    int temp = 0;
    int a_val = 0;
    int b_val = 0;
    // 第一次处理
    a_val = a.val;
    b_val = b.val;
    // 大于等于10
    int val_sum = a_val + b_val + temp;
    if (val_sum >= 10) {
      temp = val_sum / 10;
    }
    // 小于10
    int real_val = val_sum % 10;
    result = new ListNode(real_val);
    ListNode a_current = a.next;
    ListNode b_current = b.next;
    while (a_current != null || b_current != null) {
      if (a_current != null) {
        a_val = a_current.val;
        a_current = a_current.next;
      }
      if (b_current != null) {
        b_val = b_current.val;
        b_current = b_current.next;
      }
      // 上次加的整数
      val_sum = a_val + b_val + temp;
      // 大于等于10
      if (val_sum >= 10) {
        temp = val_sum / 10;
      }
      // 小于10
      real_val = val_sum % 10;
      result.next = new ListNode(real_val);
    }
    // temp >
    return result;
  }
}
