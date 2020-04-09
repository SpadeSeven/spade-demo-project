package com.zhang.demo.algorithms.bst;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ValidBSTTest {

  private ValidBST valider;

  @Before
  public void setUp() {
    valider = new ValidBST();
  }

  @Test
  public void test_isValidBST_1() {
    TreeNode root = new TreeNode(2);
    root.left = new TreeNode(1);
    root.right = new TreeNode(3);

    Assertions.assertThat(valider.isValidBST(root)).isTrue();
  }

  @Test
  public void test_isValidBST_2() {
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(1);
    root.right = new TreeNode(4);
    root.right.left = new TreeNode(3);
    root.right.right = new TreeNode(6);

    Assertions.assertThat(valider.isValidBST(root)).isFalse();
  }

  @Test
  public void test_isValidBST_3() {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(15);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(20);

    Assertions.assertThat(valider.isValidBST(root)).isFalse();
  }
}
