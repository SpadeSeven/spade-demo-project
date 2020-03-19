package com.zhang.demo.algorithms.tree.traversal;

import com.google.common.collect.Lists;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class LevelOrderTest {

  private LevelOrder order;

  @Before
  public void setUp() {
    order = new LevelOrder();
  }

  @Test
  public void test_levelOrder() {
    TreeNode root = new TreeNode(3);
    TreeNode rootLeft = new TreeNode(9);
    TreeNode rootRight = new TreeNode(20);
    TreeNode rootRightLeft = new TreeNode(15);
    TreeNode rootRightRight = new TreeNode(7);

    root.left = rootLeft;
    root.right = rootRight;
    rootRight.left = rootRightLeft;
    rootRight.right = rootRightRight;

    List<Integer> level_1 = Lists.newArrayList();
    List<Integer> level_2 = Lists.newArrayList();
    List<Integer> level_3 = Lists.newArrayList();
    level_1.add(3);
    level_2.add(9);
    level_2.add(20);
    level_3.add(15);
    level_3.add(7);
    List<List<Integer>> expected = Lists.newArrayList();
    expected.add(level_1);
    expected.add(level_2);
    expected.add(level_3);

    List<List<Integer>> result = order.levelOrder(root);
    Assertions.assertThat(result).isEqualTo(expected);
  }
}
