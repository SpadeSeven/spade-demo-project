package com.zhang.demo.algorithms.tree.traversal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class IsSymmetricTreeTest {

  private IsSymmetricTree isSymmetricTree;

  @Before
  public void setUp() {
    isSymmetricTree = new IsSymmetricTree();
  }

  @Test
  public void test_IsSymmetric_1() {
    TreeNode node = null;
    Assertions.assertThat(isSymmetricTree.isSymmetric(node)).isTrue();
  }

  @Test
  public void test_IsSymmetric_2() {
    TreeNode root = new TreeNode(1);
    TreeNode rootLeft = new TreeNode(2);
    TreeNode rootRight = new TreeNode(2);
    TreeNode rootLeftLeft = new TreeNode(3);
    TreeNode rootLeftRight = new TreeNode(4);
    TreeNode rootRightLeft = new TreeNode(4);
    TreeNode rootRightRight = new TreeNode(3);
    root.left = rootLeft;
    root.right = rootRight;
    rootLeft.left = rootLeftLeft;
    rootLeft.right = rootLeftRight;
    rootRight.left = rootRightLeft;
    rootRight.right = rootRightRight;

    Assertions.assertThat(isSymmetricTree.isSymmetric(root)).isTrue();
  }

  @Test
  public void test_IsSymmetric_3() {
    TreeNode root = new TreeNode(1);
    TreeNode rootLeft = new TreeNode(2);
    TreeNode rootRight = new TreeNode(2);
    TreeNode rootLeftLeft = new TreeNode(3);
    TreeNode rootRightRight = new TreeNode(3);
    root.left = rootLeft;
    root.right = rootRight;
    rootLeft.left = rootLeftLeft;
    rootRight.right = rootRightRight;

    Assertions.assertThat(isSymmetricTree.isSymmetric(root)).isTrue();
  }

  @Test
  public void test_IsSymmetric_4() {
    // [9,-42,-42,null,76,76,null,null,13,null,13]
    TreeNode root = new TreeNode(9);
    TreeNode rootLeft = new TreeNode(-42);
    TreeNode rootRight = new TreeNode(-42);
    TreeNode rootLeftRight = new TreeNode(76);
    TreeNode rootRightLeft = new TreeNode(76);
    TreeNode rootLeftRightRight = new TreeNode(13);
    TreeNode rootRightLeftLeft = new TreeNode(13);
    root.left = rootLeft;
    root.right = rootRight;
    rootLeft.right = rootLeftRight;
    rootRight.left = rootRightLeft;
    rootLeftRight.right = rootLeftRightRight;
    rootRightLeft.right = rootRightLeftLeft;

    Assertions.assertThat(isSymmetricTree.isSymmetric(root)).isFalse();
  }
}
