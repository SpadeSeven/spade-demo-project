package com.zhang.demo.part1.singleton;

/**
 * Created by Administrator on 2019-02-26.
 */
public class SingletonC {

  private static volatile SingletonC singletonC = null;

  private SingletonC() {

  }

  public static SingletonC getSingletonC() {
    synchronized (SingletonC.class) {
      if (singletonC == null) {
        singletonC = new SingletonC();
      }
      return singletonC;
    }
  }
}
