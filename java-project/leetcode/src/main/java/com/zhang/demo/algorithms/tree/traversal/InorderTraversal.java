package com.zhang.demo.algorithms.tree.traversal;

import java.util.ArrayList;
import java.util.List;

/** 二叉树的中序遍历 中序遍历是先遍历左子树，然后访问根节点，然后遍历右子树。 */
public class InorderTraversal {

  List<Integer> result = new ArrayList<>();

  public List<Integer> inorderTraversal(TreeNode root) {

    if (root == null) {
      return result;
    }

    if (root.left != null && root.right != null) {
      inorderTraversal(root.left);
      result.add(root.val);
      inorderTraversal(root.right);
    } else if (root.left != null) {
      inorderTraversal(root.left);
      result.add(root.val);
    } else if (root.right != null) {
      result.add(root.val);
      inorderTraversal(root.right);
    } else {
      result.add(root.val);
    }

    return result;
  }
}
