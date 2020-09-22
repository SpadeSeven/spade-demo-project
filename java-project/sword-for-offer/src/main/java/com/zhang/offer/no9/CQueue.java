package com.zhang.offer.no9;

import java.util.Stack;

public class CQueue {

  private Stack<Integer> stack_1 = new Stack<>();

  private Stack<Integer> stack_2 = new Stack<>();

  public CQueue() {}

  public void appendTail(int value) {
    stack_1.push(value);
  }

  public int deleteHead() {
    if (stack_1.empty()) return -1;
    while (!stack_1.empty()) {
      stack_2.push(stack_1.pop());
    }

    int result = stack_2.pop();

    while (!stack_2.empty()) {
      stack_1.push(stack_2.pop());
    }
    return result;
  }
}
