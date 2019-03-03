package com.zhang.demo.part1.singleton;

/**
 * Created by Administrator on 2019-02-26.
 */
public class SingletonA {

  private static SingletonA singletonA = new SingletonA();

  private SingletonA() {
  }

  public static SingletonA getSingletonA() {
    return singletonA;
  }

}
