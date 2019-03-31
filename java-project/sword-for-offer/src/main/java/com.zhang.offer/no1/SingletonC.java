package com.zhang.offer.no1;

/**
 * Created by Administrator on 2019-04-01.
 *
 * 懒汉模式，线程安全，但是效率不高
 */
public class SingletonC {

  private static SingletonC instance;

  private SingletonC() {
  }

  public synchronized static SingletonC getInstance() {
    if (instance == null) {
      instance = new SingletonC();
    }
    return instance;
  }

}
