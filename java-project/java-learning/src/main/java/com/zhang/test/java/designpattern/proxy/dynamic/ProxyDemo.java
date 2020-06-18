package com.zhang.test.java.designpattern.proxy.dynamic;

/**
 * @author 93908
 */
public class ProxyDemo {

  public static void main(String[] args) {
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    Agency agency = new Agency();
    Real real = new Real();
    Object obj = agency.bind(real);
    Subject subject = (Subject) obj;
    subject.dosomething();
    DupSubject dupSubject = (DupSubject) obj;
    dupSubject.dosomethingagain();
  }
}
