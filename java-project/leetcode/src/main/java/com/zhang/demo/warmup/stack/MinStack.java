package com.zhang.demo.warmup.stack;

import java.util.Stack;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。 </b> push(x) -- 将元素 x 推入栈中。 </b> pop() -- 删除栈顶的元素。 </b>
 * top() -- 获取栈顶元素。 </b> getMin() -- 检索栈中的最小元素。
 */
public class MinStack {

  private Stack<Integer> data;
  private Stack<Integer> helper;

  /** initialize your data structure here. */
  public MinStack() {
    data = new Stack<>();
    helper = new Stack<>();
  }

  /** 将元素 x 推入栈中 */
  public void push(int x) {
    data.push(x);
    // 始终保持最小的元素在help的栈顶
    if (helper.empty() || helper.peek() >= x) {
      helper.push(x);
    } else {
      // 新元素比较大，就让最小的元素占位
      helper.push(helper.peek());
    }
  }

  /** 删除栈顶的元素 */
  public void pop() {
    data.pop();
    helper.pop();
  }

  /** 获取栈顶元素 */
  public int top() {
    return data.peek();
  }

  /** 常数时间检索到 */
  public int getMin() {
    return helper.peek();
  }
}
