package com.zhang.demo.part1.singleton;

/**
 * Created by Administrator on 2019-02-26.
 */
public class SingletonB {

  private static SingletonB singletonB = null;

  private SingletonB() {
  }

  public static SingletonB getSingletonB() {
    if (singletonB == null) {
      singletonB = new SingletonB();
    }
    return singletonB;
  }

}
