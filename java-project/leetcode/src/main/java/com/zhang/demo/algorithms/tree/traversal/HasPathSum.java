package com.zhang.demo.algorithms.tree.traversal;

public class HasPathSum {

  public boolean hasPathSum(TreeNode root, int sum) {

    if (root == null) return sum == 0;

    return minTotal(root, root.val) == sum;
  }

  public int minTotal(TreeNode root, int sum) {

    if (root.left != null && root.right != null) {
      if (root.left.val > root.right.val) {
        sum = minTotal(root.left, sum + root.left.val);
      } else {
        sum = minTotal(root.right, sum + root.right.val);
      }
    } else if (root.left != null) {
      sum = minTotal(root.left, sum + root.left.val);
    } else if (root.right != null) {
      sum = minTotal(root.right, sum + root.right.val);
    }

    return sum;
  }
}
