package com.zhang.offer.no9;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CQueueTest {

  private CQueue cQueue;

  @Before
  public void setUp() {
    cQueue = new CQueue();
  }

  @Test
  public void test_1() {
    cQueue.appendTail(3);
    Assertions.assertThat(cQueue.deleteHead()).isEqualTo(3);
    Assertions.assertThat(cQueue.deleteHead()).isEqualTo(-1);
  }

  @Test
  public void test_2() {
    Assertions.assertThat(cQueue.deleteHead()).isEqualTo(-1);
    cQueue.appendTail(5);
    cQueue.appendTail(2);
    Assertions.assertThat(cQueue.deleteHead()).isEqualTo(5);
    Assertions.assertThat(cQueue.deleteHead()).isEqualTo(2);
  }
}
