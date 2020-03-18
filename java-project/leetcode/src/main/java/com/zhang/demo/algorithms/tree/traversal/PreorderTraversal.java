package com.zhang.demo.algorithms.tree.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal {

  private List<Integer> list = new ArrayList<>();

  public List<Integer> preorderTraversal(TreeNode root) {

    if (root != null && root.left != null) {
      list.add(root.val);
      preorderTraversal(root.left);
      preorderTraversal(root.right);
    } else if (root != null && root.right != null) {
      list.add(root.val);
      preorderTraversal(root.right);
    } else if (root != null) {
      list.add(root.val);
    }
    return list;
  }

  public List<Integer> preorderTraversal_iterator(TreeNode root) {

    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> output = new LinkedList<>();

    if (root == null) {
      return output;
    }

    stack.add(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pollLast();
      output.add(node.val);
      if (node.right != null) {
        stack.add(node.right);
      }
      if (node.left != null) {
        stack.add(node.left);
      }
    }

    return output;
  }
}
