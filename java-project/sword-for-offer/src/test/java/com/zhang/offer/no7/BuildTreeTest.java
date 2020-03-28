package com.zhang.offer.no7;

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
  public void test_buildTree_1() {
    int[] preorder = {3, 9, 20, 15, 7};
    int[] inorder = {9, 3, 15, 20, 7};

    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    Assertions.assertThat(builder.buildTree(preorder, inorder)).isEqualTo(root);
  }

  @Test
  public void test_buildTree_2() {
    int[] preorder = {};
    int[] inorder = {};

    Assertions.assertThat(builder.buildTree(preorder, inorder)).isEqualTo(null);
  }
}
