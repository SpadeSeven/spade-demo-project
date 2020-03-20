package com.zhang.demo.algorithms.tree.traversal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class HasPathSumTest {

  private HasPathSum hasPathSum;

  @Before
  public void setUp() {
    hasPathSum = new HasPathSum();
  }

  @Test
  public void test_HasPathSum() {
    TreeNode root = new TreeNode(5);
    TreeNode rootLeft = new TreeNode(4);
    TreeNode rootRight = new TreeNode(8);
    TreeNode rootLeftLeft = new TreeNode(11);
    TreeNode rootRightLeft = new TreeNode(13);
    TreeNode rootRightRight = new TreeNode(4);
    TreeNode rootLeftLeftLeft = new TreeNode(7);
    TreeNode rootLeftLeftRight = new TreeNode(2);
    TreeNode rootRightRightRight = new TreeNode(1);
    root.left = rootLeft;
    root.right = rootRight;
    rootLeft.left = rootLeftLeft;
    rootRight.left = rootRightLeft;
    rootRight.right = rootRightRight;
    rootLeftLeft.left = rootLeftLeftLeft;
    rootLeftLeft.right = rootLeftLeftRight;
    rootRightRight.right = rootRightRightRight;

    Assertions.assertThat(hasPathSum.hasPathSum(root, 22)).isFalse();
  }
}
