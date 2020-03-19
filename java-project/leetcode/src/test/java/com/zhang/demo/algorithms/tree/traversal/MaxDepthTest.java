package com.zhang.demo.algorithms.tree.traversal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class MaxDepthTest {

  private MaxDepth maxDepth;

  @Before
  public void setUp() {
    maxDepth = new MaxDepth();
  }

  @Test
  public void test_MaxDepth() {
    TreeNode root = new TreeNode(3);
    TreeNode rootLeft = new TreeNode(9);
    TreeNode rootRight = new TreeNode(20);
    TreeNode rootRightLeft = new TreeNode(15);
    TreeNode rootRightRight = new TreeNode(7);

    root.left = rootLeft;
    root.right = rootRight;
    rootRight.left = rootRightLeft;
    rootRight.right = rootRightRight;

    Assertions.assertThat(maxDepth.maxDepth(root)).isEqualTo(3);
  }
}
