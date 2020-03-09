package com.zhang.demo.warmup.stack;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MinStackTest {

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
}
