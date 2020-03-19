package com.zhang.demo.algorithms.tree.traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的层次遍历
 *
 * <p>给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class LevelOrder {

  List<List<Integer>> result = new ArrayList<>();
  List<Integer> level = new ArrayList<>();

  public List<List<Integer>> levelOrder(TreeNode root) {

    if (root == null) {
      return result;
    }

    return levelOrder(root, 0);
  }

  public List<List<Integer>> levelOrder(TreeNode root, int treeLevel) {

    if (treeLevel == result.size()) {
      level = new ArrayList<>();
      result.add(level);
    }
    result.get(treeLevel).add(root.val);

    if (root.left != null && root.right != null) {
      levelOrder(root.left, treeLevel + 1);
      levelOrder(root.right, treeLevel + 1);
    } else if (root.left != null) {
      levelOrder(root.left, treeLevel + 1);
    } else if (root.right != null) {
      levelOrder(root.right, treeLevel + 1);
    }

    return result;
  }
}
