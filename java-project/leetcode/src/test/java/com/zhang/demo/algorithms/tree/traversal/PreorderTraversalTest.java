package com.zhang.demo.algorithms.tree.traversal;

import com.google.common.collect.Lists;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PreorderTraversalTest {

  @Test
  public void test_preorderTraversal_1() {
    PreorderTraversal traversal = new PreorderTraversal();

    TreeNode root = new TreeNode(1);
    TreeNode rightNode = new TreeNode(2);
    TreeNode rightLeftNode = new TreeNode(3);

    // [1,null,2,3]
    rightNode.left = rightLeftNode;
    root.right = rightNode;

    List<Integer> expected = Lists.newArrayList();
    expected.add(1);
    expected.add(2);
    expected.add(3);

    Assertions.assertThat(traversal.preorderTraversal(root)).isEqualTo(expected);
  }

  @Test
  public void test_preorderTraversal_2() {
    PreorderTraversal traversal = new PreorderTraversal();

    TreeNode root = new TreeNode(3);
    TreeNode leftNode = new TreeNode(1);
    TreeNode rightNode = new TreeNode(2);

    // [1,null,2,3]
    root.left = leftNode;
    root.right = rightNode;

    List<Integer> expected = Lists.newArrayList();
    expected.add(3);
    expected.add(1);
    expected.add(2);

    Assertions.assertThat(traversal.preorderTraversal(root)).isEqualTo(expected);
  }
}
