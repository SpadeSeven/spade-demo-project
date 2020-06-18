package com.zhang.test.java.designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Agency implements InvocationHandler {

  private Real sub;

  public Object bind(Real sub) {
    this.sub = sub;
    Object obj = Proxy
        .newProxyInstance(this.getClass().getClassLoader(), sub.getClass().getInterfaces(), this);
    return obj;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("  Agency begin ");
    method.invoke(sub, args);
    System.out.println(" agency end ");
    return null;
  }
}
