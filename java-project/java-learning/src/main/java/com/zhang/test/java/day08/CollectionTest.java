package com.zhang.test.java.day08;

import java.util.ArrayList;
import java.util.Collection;

/** Created by Administrator on 2018/6/3 0003. */
public class CollectionTest {
  public static void main(String[] args) {
    Collection c = new ArrayList();
    c.add("孙悟空");
    c.add(6);
    System.out.println("集合的个数: " + c.size());

    c.remove(6);
    System.out.println("集合的个数: " + c.size());
  }
}
