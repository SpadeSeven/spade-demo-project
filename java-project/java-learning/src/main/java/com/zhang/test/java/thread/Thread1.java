package com.zhang.test.java.thread;

public class Thread1 implements Runnable {

  @Override
  public void run() {
    synchronized (this) {
      for (int index = 0; index < 5; index++) {
        System.out.println(Thread.currentThread().getName() + " synchronized loop " + index);
      }
    }
  }

  public static void main(String[] args) {
    Thread1 thread1 = new Thread1();
    Thread t1 = new Thread(thread1, "t1");
    Thread t2 = new Thread(thread1, "t2");
    t1.start();
    t2.start();
  }
}
