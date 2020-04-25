package com.zhang.demo.algorithms.tree.traversal;

public class Node {

  int val;
  /** 左子节点 */
  Node left;
  /** 右子节点 */
  Node right;
  /** 右侧节点 */
  Node next;

  public Node(int val) {
    this.val = val;
  }

  public Node(int val, Node left, Node right, Node next) {
    this.val = val;
    this.left = left;
    this.right = right;
    this.next = next;
  }

  public int getVal() {
    return val;
  }

  public void setVal(int val) {
    this.val = val;
  }
}
