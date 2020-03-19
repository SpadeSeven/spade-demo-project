package com.zhang.demo.algorithms.tree.traversal;

public class MaxDepth {

  private int max = 0;

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return max;
    }

    return maxDepth(root, 1);
  }

  public int maxDepth(TreeNode node, int level) {

    if (node.left != null) {
      int left = maxDepth(node.left, level + 1);
      max = Math.max(max, left);
    }

    if (node.right != null) {
      int right = maxDepth(node.right, level + 1);
      max = Math.max(right, max);
    }

    max = Math.max(max, level);

    return max;
  }
}
