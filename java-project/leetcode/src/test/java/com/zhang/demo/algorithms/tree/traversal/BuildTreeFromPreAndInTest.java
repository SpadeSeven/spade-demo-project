package com.zhang.demo.algorithms.tree.traversal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BuildTreeFromPreAndInTest {

  private BuildTreeFromPreAndIn builder;

  @Before
  public void setUp() {
    builder = new BuildTreeFromPreAndIn();
  }

  @Test
  public void test_BuildTree() {
    int[] preorder = {3, 9, 20, 15, 7};
    int[] inorder = {9, 3, 15, 20, 7};

    TreeNode root = builder.buildTree(preorder, inorder);
    Assertions.assertThat(root.val).isEqualTo(3);
    Assertions.assertThat(root.left.val).isEqualTo(9);
    Assertions.assertThat(root.right.val).isEqualTo(20);
    Assertions.assertThat(root.right.left.val).isEqualTo(15);
    Assertions.assertThat(root.right.right.val).isEqualTo(7);
  }
}
