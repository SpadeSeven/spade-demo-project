package com.zhang.offer.no7;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

/** 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 */
public class BuildTree {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0) return null;

    int root = preorder[0];

    return null;
  }
}
