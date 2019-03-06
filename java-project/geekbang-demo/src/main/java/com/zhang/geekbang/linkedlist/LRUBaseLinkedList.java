package com.zhang.geekbang.linkedlist;

import java.util.Date;

/**
 * Created by Administrator on 2019-02-21.
 */
public class LRUBaseLinkedList<T> {

  // 默认的链表容量
  private final static int DEFAULT_CAPACITY = 10;

  // 头结点
  private SNode<T> headNode;

  // 链表长度
  private int length;

  // 链表容量
  private int capacity;

  public LRUBaseLinkedList() {
    this.headNode = new SNode<>();
    this.capacity = DEFAULT_CAPACITY;
    this.length = 0;
  }

  public LRUBaseLinkedList(int capacity) {
    this.headNode = new SNode<>();
    this.capacity = capacity;
    this.length = 0;
  }

  /**
   * 增加一个节点
   */
  private void add(T data) {
    SNode preNode = findPreNode(data);

    // 如果前驱节点存在
    if (preNode != null) {
      deleteElementOptim(preNode);

    }
  }

  /**
   * 查找前驱节点
   */
  private SNode findPreNode(T data) {
    SNode node = headNode;
    while (node.getNext() != null) {
      if (data.equals(node.getNext().getElement())) {
        return node;
      }
    }
    return null;
  }

  /**
   * 删除当前节点的下一个节点
   */
  private void deleteElementOptim(SNode preNode) {
    SNode temp = preNode.getNext();
    preNode.setNext(temp.getNext());
    temp = null;
    length--;
  }

  /**
   * 在头部插入节点
   * @param data
   */
  private void insertElementAtBegin(T data) {
    SNode next = headNode.getNext();
    headNode.setNext(new SNode(data, next));
    length++;
  }

  // 节点
  public class SNode<T> {

    private T element;

    private SNode next;

    private SNode(T element) {
      this.element = element;
    }

    private SNode(T element, SNode next) {
      this.element = element;
      this.next = next;
    }

    public SNode() {
      this.next = null;
    }

    public T getElement() {
      return element;
    }

    public void setElement(T element) {
      this.element = element;
    }

    public SNode getNext() {
      return next;
    }

    public void setNext(SNode next) {
      this.next = next;
    }
  }
}
