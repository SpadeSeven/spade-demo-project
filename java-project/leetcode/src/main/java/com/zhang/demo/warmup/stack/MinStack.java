package com.zhang.demo.warmup.stack;

import java.util.Arrays;

/** 最小栈，数组实现 */
public class MinStack {

  private int[] stack;
  private int[] minstack;
  private int capacity;
  private int size;

  /** initialize your data structure here. */
  public MinStack() {
    stack = new int[16];
    minstack = new int[16];
    capacity = 16;
    size = 0;
  }

  public void push(int x) {
    ensureEnough();
    if (size == 0) {
      stack[size] = x;
      minstack[size] = x;
    } else {
      stack[size] = x;
      if (minstack[size - 1] >= x) {
        minstack[size] = x;
      } else {
        minstack[size] = minstack[size - 1];
      }
    }
    size++;
  }

  public void pop() {
    size--;
  }

  public int top() {
    return stack[size - 1];
  }

  public int getMin() {
    return minstack[size - 1];
  }

  public void ensureEnough() {
    if (size >= capacity - 1) {
      capacity *= 2;
      stack = Arrays.copyOf(stack, capacity);
      minstack = Arrays.copyOf(minstack, capacity);
    }
  }
}
