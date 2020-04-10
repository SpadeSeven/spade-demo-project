package com.zhang.demo.algorithms.tree.traversal;

import java.util.Arrays;
import java.util.HashMap;

/** 从前序与中序遍历序列构造二叉树 */
public class BuildTreeFromPreAndIn {

  public TreeNode buildTree(int[] preorder, int[] inorder) {

    return buildTree(preorder, inorder, null);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder, TreeNode root) {
    if (preorder.length == 0) {
      return null;
    } else if (preorder.length == 1) {
      return new TreeNode(preorder[0]);
    }

    HashMap<Integer, Integer> inorderMap = new HashMap();
    for (int index = 0; index < inorder.length; index++) {
      inorderMap.put(inorder[index], index);
    }

    int rootValue = preorder[0];
    root = new TreeNode(rootValue);
    int LeftRootIndex = inorderMap.get(rootValue);
    // 左子树
    TreeNode left =
        buildTree(
            Arrays.copyOfRange(preorder, 1, LeftRootIndex + 1),
            Arrays.copyOfRange(inorder, 0, LeftRootIndex),
            null);
    ;
    // 右子树
    TreeNode right =
        buildTree(
            Arrays.copyOfRange(preorder, LeftRootIndex + 1, preorder.length),
            Arrays.copyOfRange(inorder, LeftRootIndex + 1, inorder.length),
            null);

    root.left = left;
    root.right = right;
    return root;
  }
}
