package com.zhang.demo.algorithms.tree.traversal;

import com.google.common.collect.Lists;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class PostorderTraversalTest {

  private PostorderTraversal traversal;

  @Before
  public void setUp() {
    traversal = new PostorderTraversal();
  }

  @Test
  public void postorderTraversal() {
    TreeNode root = new TreeNode(1);
    TreeNode rightNode = new TreeNode(2);
    TreeNode rightLeftNode = new TreeNode(3);

    // [1,null,2,3]
    rightNode.left = rightLeftNode;
    root.right = rightNode;

    List<Integer> expected = Lists.newArrayList();
    expected.add(3);
    expected.add(2);
    expected.add(1);

    Assertions.assertThat(traversal.postorderTraversal(root)).isEqualTo(expected);
  }
}
