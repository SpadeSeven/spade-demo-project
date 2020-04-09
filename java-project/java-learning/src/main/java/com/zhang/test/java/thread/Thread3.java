package com.zhang.test.java.thread;

public class Thread3 {
  public void t1() {
    synchronized (this) {
      int index = 5;
      while (index-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : " + index);
      }
    }
  }

  public void t2() {
    synchronized (this) {
      int index = 5;
      while (index-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : " + index);
      }
    }
  }

  public static void main(String[] args) {
    Thread3 thread3 = new Thread3();
    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                thread3.t1();
              }
            },
            "t1");
    Thread t2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                thread3.t2();
              }
            },
            "t2");
    t1.start();
    t2.start();
  }
}
