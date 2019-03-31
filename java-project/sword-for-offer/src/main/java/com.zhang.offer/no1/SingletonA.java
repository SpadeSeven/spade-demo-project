package com.zhang.offer.no1;

/**
 * Created by Administrator on 2019-04-01.
 *
 * 饿汉模式，线程安全
 */
public class SingletonA {

  private static final SingletonA INSTANCE = new SingletonA();

  private SingletonA() {
  }

  public static SingletonA getInstance() {
    return INSTANCE;
  }


}
