package com.zhang.offer.no2;

/**
 * Created by Administrator on 2019-04-01.
 *
 * <p>懒汉模式，线程不安全
 */
public class SingletonB {

  private static SingletonB instance;

  private SingletonB() {}

  public static SingletonB getInstance() {
    if (instance == null) {
      instance = new SingletonB();
    }
    return instance;
  }
}
