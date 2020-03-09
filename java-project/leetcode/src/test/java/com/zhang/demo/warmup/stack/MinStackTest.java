package com.zhang.demo.warmup.stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MinStackTest {

  @Test
  public void test_old() {
    MinStackOld minStack = new MinStackOld();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    Assertions.assertThat(minStack.getMin()).isEqualTo(-3);
    minStack.pop();
    Assertions.assertThat(minStack.top()).isEqualTo(0);
    Assertions.assertThat(minStack.getMin()).isEqualTo(-2);
  }

  @Test
  public void test() {
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    Assertions.assertThat(minStack.getMin()).isEqualTo(-3);
    minStack.pop();
    Assertions.assertThat(minStack.top()).isEqualTo(0);
    Assertions.assertThat(minStack.getMin()).isEqualTo(-2);
  }

  @Test
  public void test_1() {
    MinStack minStack = new MinStack();
    minStack.push(85);
    minStack.push(-99);
    minStack.push(67);
    minStack.getMin();
    minStack.push(-27);
    minStack.push(61);
    minStack.push(-97);
    minStack.push(-27);
    minStack.push(35);
    minStack.top();
    minStack.push(99);
    minStack.push(-66);
    minStack.getMin();
    minStack.push(-89);
    minStack.getMin();
    minStack.push(4);
    minStack.push(-70);
    minStack.getMin();
    minStack.push(52);
    minStack.top();
    minStack.push(54);
    minStack.getMin();
    minStack.push(94);
    minStack.push(-41);
    minStack.push(-75);
    minStack.push(-32);
    minStack.getMin();
    minStack.push(5);
    minStack.push(29);
    minStack.top();
    minStack.push(-78);
    minStack.push(-74);
    minStack.getMin();
    minStack.pop();
    minStack.getMin();
    minStack.push(-58);
    minStack.push(-44);
    minStack.getMin();
    minStack.getMin();
    minStack.push(-64);
    minStack.getMin();
    minStack.pop();
    minStack.push(-45);
    minStack.push(-99);
    minStack.push(-27);
    minStack.getMin();
    minStack.push(-96);
    minStack.getMin();
    minStack.getMin();
    minStack.getMin();
    minStack.pop();
    minStack.getMin();
    minStack.push(26);
    minStack.push(-58);
    minStack.getMin();
    minStack.top();
    minStack.getMin();
    minStack.push(25);
    minStack.getMin();
    minStack.getMin();
    minStack.getMin();
    minStack.getMin();
    minStack.push(33);
    minStack.getMin();
    minStack.getMin();
    minStack.getMin();
    minStack.push(71);
    minStack.getMin();
    minStack.push(-62);
    minStack.getMin();
    minStack.push(-78);
    minStack.getMin();
    minStack.getMin();
    minStack.getMin();
    minStack.getMin();
    minStack.pop();
    minStack.getMin();
    minStack.push(-30);
    minStack.getMin();
    minStack.getMin();
    minStack.push(85);
    minStack.push(-15);
    minStack.pop();
    minStack.push(-40);
    minStack.getMin();
    minStack.push(72);
    minStack.top();
    minStack.top();
    minStack.push(18);
    minStack.push(59);
    minStack.getMin();
    minStack.pop();
    minStack.getMin();
    minStack.push(-59);
    minStack.top();
    minStack.push(10);
    minStack.getMin();
    minStack.push(9);
    minStack.getMin();
    minStack.getMin();
  }
}
