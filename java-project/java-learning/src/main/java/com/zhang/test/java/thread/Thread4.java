package com.zhang.test.java.thread;

public class Thread4 {
  public void t1() {
    synchronized (this) {
      int index = 5;
      while (index-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : " + index);
      }
    }
  }

  public synchronized void t2() {
    int index = 5;
    while (index-- > 0) {
      System.out.println(Thread.currentThread().getName() + " : " + index);
    }
  }

  public static void main(String[] args) {
    Thread4 thread4 = new Thread4();
    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                thread4.t1();
              }
            },
            "t1");
    Thread t2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                thread4.t2();
              }
            },
            "t2");
    t1.start();
    t2.start();
  }
}
