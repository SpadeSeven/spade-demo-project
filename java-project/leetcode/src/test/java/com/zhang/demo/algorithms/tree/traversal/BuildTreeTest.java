package com.zhang.demo.algorithms.tree.traversal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BuildTreeTest {

  private BuildTree builder;

  @Before
  public void setUp() {
    builder = new BuildTree();
  }

  @Test
  public void test_BuildTree_1() {
    int[] inorder = {9, 3, 15, 20, 7};
    int[] postorder = {9, 15, 7, 20, 3};

    TreeNode result = builder.buildTree(inorder, postorder);
    Assertions.assertThat(result.val).isEqualTo(3);
    Assertions.assertThat(result.left.val).isEqualTo(9);
    Assertions.assertThat(result.right.val).isEqualTo(20);
    Assertions.assertThat(result.right.left.val).isEqualTo(15);
    Assertions.assertThat(result.right.right.val).isEqualTo(7);
  }
}
