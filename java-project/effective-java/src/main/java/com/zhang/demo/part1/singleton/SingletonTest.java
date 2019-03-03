
package com.zhang.demo.part1.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2019-02-26.
 */
public class SingletonTest {

  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
    SingletonA singletonA1 = SingletonA.getSingletonA();
    SingletonA singletonA2 = SingletonA.getSingletonA();
    System.out.println(singletonA1);
    System.out.println(singletonA2);

    Class clazz = SingletonA.class;
    Constructor cons = clazz.getDeclaredConstructor(null);
    cons.setAccessible(true);

    SingletonA singletonA3 = (SingletonA) cons.newInstance(null);
    System.out.println(singletonA3);
  }
}
