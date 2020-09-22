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

    TreeNode actual = builder.buildTree(preorder, inorder);
    Assertions.assertThat(actual.val).isEqualTo(3);
    Assertions.assertThat(actual.left.val).isEqualTo(9);
    Assertions.assertThat(actual.right.val).isEqualTo(20);
    Assertions.assertThat(actual.right.left.val).isEqualTo(15);
    Assertions.assertThat(actual.right.right.val).isEqualTo(7);
  }

  @Test
  public void test_buildTree_2() {
    int[] preorder = {1, 2};
    int[] inorder = {2, 1};

    TreeNode actual = builder.buildTree(preorder, inorder);
    Assertions.assertThat(actual.val).isEqualTo(1);
    Assertions.assertThat(actual.left.val).isEqualTo(2);
  }

  @Test
  public void test_buildTree_3() {
    int[] preorder = {1};
    int[] inorder = {1};

    TreeNode actual = builder.buildTree(preorder, inorder);
    Assertions.assertThat(actual.val).isEqualTo(1);
  }

  @Test
  public void test_buildTree_4() {
    int[] preorder = {1, 2};
    int[] inorder = {1, 2};

    TreeNode actual = builder.buildTree(preorder, inorder);
    Assertions.assertThat(actual.val).isEqualTo(1);
    Assertions.assertThat(actual.right.val).isEqualTo(2);
    Assertions.assertThat(actual.left).isEqualTo(null);
  }
}
