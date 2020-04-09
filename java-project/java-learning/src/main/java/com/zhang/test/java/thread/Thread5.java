package com.zhang.test.java.thread;

public class Thread5 {

  class Inner {
    private void m1() {
      int index = 5;
      while (index-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : Inner-m1 :  " + index);
      }
    }

    private void m2() {
      int index = 5;
      while (index-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : Inner-m2 : " + index);
      }
    }
  }

  private void t1(Inner inner) {
    synchronized (inner) {
      inner.m1();
    }
  }

  private void t2(Inner inner) {
    inner.m2();
  }

  public static void main(String[] args) {
    final Thread5 thread5 = new Thread5();
    final Inner inner = thread5.new Inner();
    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                thread5.t1(inner);
              }
            },
            "t1");
    Thread t2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                thread5.t2(inner);
              }
            },
            "t2");

    t1.start();
    t2.start();
  }
}
