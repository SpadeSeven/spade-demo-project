package com.zhang.demo.algorithms.bst;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * <p>假设一个二叉搜索树具有如下特征：
 *
 * <p>1、节点的左子树只包含小于当前节点的数。
 *
 * <p>2、节点的右子树只包含大于当前节点的数。
 *
 * <p>3、所有左子树和右子树自身必须也是二叉搜索树。
 */
public class ValidBST {

  public boolean isValidBST(TreeNode root) {

    if (root == null) {
      return true;
    }

    if (root.left != null) {
      if (root.left.val >= root.val) {
        return false;
      }
      isValidBST(root.left);
    }

    if (root.right != null) {
      if (root.right.val <= root.val) {
        return false;
      }
      isValidBST(root.right);
    }

    return true;
  }
}
