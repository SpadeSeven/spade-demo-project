package com.zhang.demo.part1.singleton;

/**
 * Created by Administrator on 2019-02-26.
 */
public class SingletonD {

  private static volatile SingletonD singletonD = null;

  private SingletonD() {

  }

  public static SingletonD getSingletonD() {
    if (singletonD == null) {
      synchronized (SingletonD.class) {
        if (singletonD == null) {
          singletonD = new SingletonD();
        }
      }
    }
    return singletonD;
  }

}
