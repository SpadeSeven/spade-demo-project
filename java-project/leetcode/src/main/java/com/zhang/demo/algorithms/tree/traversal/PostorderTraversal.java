package com.zhang.demo.algorithms.tree.traversal;

import java.util.ArrayList;
import java.util.List;

/** 二叉树的后续遍历 */
public class PostorderTraversal {

  private List<Integer> result = new ArrayList<>();

  public List<Integer> postorderTraversal(TreeNode root) {

    if (root == null) {
      return result;
    }

    if (root.left != null && root.right != null) {
      postorderTraversal(root.left);
      postorderTraversal(root.right);
      result.add(root.val);
    } else if (root.left != null) {
      postorderTraversal(root.left);
      result.add(root.val);
    } else if (root.right != null) {
      postorderTraversal(root.right);
      result.add(root.val);
    } else {
      result.add(root.val);
    }

    return result;
  }
}
