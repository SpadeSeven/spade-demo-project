package com.zhang.offer.no1;

/**
 * Created by Administrator on 2019-04-01.
 * 饿汉式，使用静态内部类，线程安全
 */
public class SingletonE {

  public final static class SingletonHolder {

    private static final SingletonE INSTANCE = new SingletonE();
  }

  private SingletonE() {
  }

  public static SingletonE getInstance() {
    return SingletonHolder.INSTANCE;
  }

}
