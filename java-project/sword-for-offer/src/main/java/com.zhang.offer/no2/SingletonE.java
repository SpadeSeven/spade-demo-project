package com.zhang.offer.no2;

/** Created by Administrator on 2019-04-01. 饿汉式，使用静态内部类，线程安全 */
public class SingletonE {

  public static final class SingletonHolder {

    private static final SingletonE INSTANCE = new SingletonE();
  }

  private SingletonE() {}

  public static SingletonE getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
