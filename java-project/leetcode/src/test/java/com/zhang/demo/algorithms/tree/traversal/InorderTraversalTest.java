package com.zhang.demo.algorithms.tree.traversal;

import com.google.common.collect.Lists;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class InorderTraversalTest {

  private InorderTraversal traversal;

  @Before
  public void setUp() {
    traversal = new InorderTraversal();
  }

  @Test
  public void test_inorderTraversal_1() {
    TreeNode root = new TreeNode(1);
    TreeNode rightNode = new TreeNode(2);
    TreeNode rightLeftNode = new TreeNode(3);

    // [1,null,2,3]
    rightNode.left = rightLeftNode;
    root.right = rightNode;

    List<Integer> expected = Lists.newArrayList();
    expected.add(1);
    expected.add(3);
    expected.add(2);

    Assertions.assertThat(traversal.inorderTraversal(root)).isEqualTo(expected);
  }

  @Test
  public void test_inorderTraversal_2() {

    TreeNode root = new TreeNode(3);
    TreeNode leftNode = new TreeNode(1);
    TreeNode rightNode = new TreeNode(2);

    // [3, 1, 2]
    root.left = leftNode;
    root.right = rightNode;

    List<Integer> expected = Lists.newArrayList();
    expected.add(1);
    expected.add(3);
    expected.add(2);

    Assertions.assertThat(traversal.inorderTraversal(root)).isEqualTo(expected);
  }
}
