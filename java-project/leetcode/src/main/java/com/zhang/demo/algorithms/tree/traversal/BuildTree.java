package com.zhang.demo.algorithms.tree.traversal;

import java.util.Arrays;
import java.util.HashMap;

/** 根据一棵树的中序遍历与后序遍历构造二叉树。 */
public class BuildTree {

  public TreeNode buildTree(int[] inorder, int[] postorder) {

    TreeNode root = null;

    return buildTree(inorder, postorder, root);
  }

  public TreeNode buildTree(int[] inorder, int[] postorder, TreeNode root) {

    if (inorder.length == 0) {
      return null;
    } else if (inorder.length == 1) {
      return new TreeNode(inorder[0]);
    }

    HashMap<Integer, Integer> inorderMap = new HashMap();
    for (int index = 0; index < inorder.length; index++) {
      inorderMap.put(inorder[index], index);
    }

    int rootValue = postorder[postorder.length - 1];
    root = new TreeNode(rootValue);
    int LeftRootIndex = inorderMap.get(rootValue);
    // 左子树
    TreeNode left =
        buildTree(
            Arrays.copyOfRange(inorder, 0, LeftRootIndex),
            Arrays.copyOfRange(postorder, 0, LeftRootIndex),
            null);
    ;
    // 右子树
    TreeNode right =
        buildTree(
            Arrays.copyOfRange(inorder, LeftRootIndex + 1, inorder.length),
            Arrays.copyOfRange(postorder, LeftRootIndex, postorder.length - 1),
            null);

    root.left = left;
    root.right = right;
    return root;
  }
}
