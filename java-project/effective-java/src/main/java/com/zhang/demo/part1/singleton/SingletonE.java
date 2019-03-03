package com.zhang.demo.part1.singleton;

/**
 * Created by Administrator on 2019-02-26.
 */
public class SingletonE {

  private static class Holder {

    private static SingletonE singletonE = new SingletonE();
  }

  private SingletonE() {
  }

  public static SingletonE getSingletonE() {
    return Holder.singletonE;
  }
}
