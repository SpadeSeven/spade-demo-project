package com.zhang.demo.algorithms.tree.traversal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ConnectTest {

  private Connect connecter;

  @Before
  public void setUp() {
    connecter = new Connect();
  }

  @Test
  public void test_connect() {
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.left = new Node(6);
    root.right.right = new Node(7);

    Node expected = new Node(1);
    expected.left = new Node(2);
    expected.right = new Node(3);
    expected.left.next = expected.right;
    expected.left.left = new Node(4);
    expected.left.left.next = expected.left.right;
    expected.left.right = new Node(5);
    expected.left.right.next = expected.right.left;
    expected.right.left = new Node(6);
    expected.right.left.next = expected.right.right;
    expected.right.right = new Node(7);

    Assertions.assertThat(connecter.connect(root)).isEqualTo(expected);
  }
}
