package com.zhang.test.java.designpattern.proxy.dynamic;

public class Real implements Subject, DupSubject {


  @Override
  public void dosomething() {
    System.out.println("----- real dosomething ----");
  }

  @Override
  public void dosomethingagain() {
    System.out.println("----- real dosomething again----");
  }

}
