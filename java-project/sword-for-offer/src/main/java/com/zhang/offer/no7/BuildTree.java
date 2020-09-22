package com.zhang.offer.no7;

import java.util.Arrays;
import java.util.HashMap;

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

    // 存储中序遍历值与下标的映射关系
    HashMap<Integer, Integer> inorderMap = new HashMap<>();
    for (int index = 0; index < inorder.length; index++) {
      inorderMap.put(inorder[index], index);
    }

    int root = preorder[0];
    if (preorder.length == 1) return new TreeNode(root);

    // 找在中序中的位置
    int indexIninorder = inorderMap.get(root);

    TreeNode rootNode = new TreeNode(root);
    // 左 子树
    int leftPreorderBeginIndex = Math.min(1, preorder.length - 1);
    int leftPreorderEndIndex = Math.min(indexIninorder + 1, preorder.length);
    rootNode.left =
        buildTree(
            Arrays.copyOfRange(preorder, leftPreorderBeginIndex, leftPreorderEndIndex),
            Arrays.copyOfRange(inorder, 0, indexIninorder));
    // 右 子树
    int rightPreorderBeginIndex = Math.min(indexIninorder + 1, preorder.length);
    int rightInorderBeginIndex = Math.min(indexIninorder + 1, inorder.length);
    rootNode.right =
        buildTree(
            Arrays.copyOfRange(preorder, rightPreorderBeginIndex, preorder.length),
            Arrays.copyOfRange(inorder, rightInorderBeginIndex, inorder.length));
    return rootNode;
  }
}
