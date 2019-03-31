package com.zhang.offer.no2;

/**
 * Created by Administrator on 2019-04-01.
 *
 * 懒汉模式，变种，线程安全，使用静态代码块
 */
public class SingletonD {

  private static SingletonD instance;

  // 静态代码块: 随着类的加载而执行，且只执行一次，并优先于主函数。用于给类初始化的。
  static {
    instance = new SingletonD();
  }

  private SingletonD() {
  }

  public static SingletonD getInstance() {
    return instance;
  }

}
