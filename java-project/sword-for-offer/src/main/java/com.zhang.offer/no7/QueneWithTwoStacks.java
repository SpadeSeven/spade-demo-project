package com.zhang.offer.no7;

import java.util.Stack;

/**
 * Created by Administrator on 2019-04-09.
 *
 * 用两个栈实现一个队列。队列的声明如下，
 * 请实现他的两个函数appendTail和deleteHead,
 * 分别完成在队列尾部插入节点和队列头部删除节点的功能
 */
public class QueneWithTwoStacks {

  private Stack<Integer> stack_1 = new Stack<>();
  private Stack<Integer> stack_2 = new Stack<>();

  public static void main(String[] args) {
    QueneWithTwoStacks quene = new QueneWithTwoStacks();
    quene.appendTail(1);
    quene.appendTail(2);
    quene.appendTail(3);
    quene.deleteHead();
    quene.deleteHead();
    quene.deleteHead();
    quene.deleteHead();
    quene.printAllElement();

  }

  public void appendTail(int element) {
    stack_1.push(element);
  }

  public void deleteHead() {
    if (stack_1.isEmpty() && stack_2.isEmpty()) {
      return;
    }
    if (stack_2.isEmpty()) {
      while (!stack_1.isEmpty()) {
        stack_2.push(stack_1.pop());
      }
    }
    stack_2.pop();
  }

  public void printAllElement() {
    if (stack_1.isEmpty() && stack_2.isEmpty()) {
      return;
    }
    if (stack_2.isEmpty()) {
      stack_2.push(stack_1.pop());
    }
    System.out.println(stack_2.pop());

  }

}
