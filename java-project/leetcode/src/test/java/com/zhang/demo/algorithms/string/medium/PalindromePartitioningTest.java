package com.zhang.demo.algorithms.string.medium;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class PalindromePartitioningTest {

  private PalindromePartitioning partitioner;

  @Before
  public void setUp() {
    partitioner = new PalindromePartitioning();
  }

  @Test
  public void test_Partition() {
    Assertions.assertThat(partitioner.partition("aab"))
        .isEqualTo(
            Lists.newArrayList(Lists.newArrayList("a", "a", "b"), Lists.newArrayList("aa", "b")));
    Assertions.assertThat(partitioner.partition("a"))
        .isEqualTo(Lists.newArrayList(Lists.newArrayList("a")));
  }
}
