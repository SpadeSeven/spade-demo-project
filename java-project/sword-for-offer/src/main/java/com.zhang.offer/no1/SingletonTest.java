package com.zhang.offer.no1;

/**
 * Created by Administrator on 2019-04-01.
 */
public class SingletonTest {

  public static void main(String[] args) {
    // 饿汉模式，线程安全
    System.out.println(SingletonA.getInstance() == SingletonA.getInstance());
    // 懒汉模式，线程不安全
    System.out.println(SingletonB.getInstance() == SingletonB.getInstance());
    // 懒汉模式，线程安全
    System.out.println(SingletonC.getInstance() == SingletonC.getInstance());
    // 懒汉模式，变种，线程安全
    System.out.println(SingletonD.getInstance() == SingletonD.getInstance());
    // 饿汉模式，静态内部类，线程安全
    System.out.println(SingletonE.getInstance() == SingletonE.getInstance());
    // 枚举方式
    System.out.println(SingletonF.INSTANCE == SingletonF.INSTANCE);
    // 双重校验锁
    System.out.println(SingletonG.getInstance() == SingletonG.getInstance());
  }

}
