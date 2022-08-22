package com.zhang.demo.algorithms.lists.hard;

import com.zhang.demo.algorithms.lists.ListNode;
import java.util.Stack;

public class No25ReverseKGroup {
  public ListNode reverseKGroup(ListNode head, int k) {
    Stack<ListNode> nodes = new Stack<>();
    ListNode temp = head;
    while (true){
      if (hasGroup(temp, k)){
        Stack<ListNode> tempNodes = new Stack<>();
        for (int index = 0; index < k; index++) {
          tempNodes.push(temp);
          temp = temp.next;
        }
        for (int index = 0; index < tempNodes.size(); index++) {
          nodes.push(tempNodes.pop());
        }
      }else {
        while (temp.next!=null){
          nodes.add(temp);
          temp = temp.next;
        }
        break;
      }
    }

    

    return new ListNode();
  }

  private boolean hasGroup(ListNode node, int k) {
    ListNode temp = node;
    for (int index = 0; index < k - 1; index++) {
      if (temp.next != null) {
        temp = node.next;
      } else {
        return false;
      }
    }

    return true;
  }
}


