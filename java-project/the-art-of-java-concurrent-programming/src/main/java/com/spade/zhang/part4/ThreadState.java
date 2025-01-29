package com.spade.zhang.part4;

/**
 * 线程状态
 *
 * @author 93908
 */
public class ThreadState {

  public static void main(String[] args) {
    new Thread(new TimeWaiting(), "TimeWaitingThread").start();
    new Thread(new Waiting(), "WaitingThread").start();
    new Thread(new Blocked(), "BlockedThread-1").start();
    new Thread(new Blocked(), "BlockedThread-2").start();
  }

  /**
   * 该线程不断进行睡眠
   */
  static class TimeWaiting implements Runnable {

    @Override
    public void run() {
      while (true) {
        SleepUtils.second(100);
      }
    }
  }

  /**
   * 该线程在 Waiting 实例上等待
   */
  static class Waiting implements Runnable {

    @Override
    public void run() {
      while (true) {
        synchronized (Waiting.class) {
          try {
            Waiting.class.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  /**
   * 该线程在 Blocked.class实例上加锁，不会释放该锁
   */
  static class Blocked implements Runnable {

    @Override
    public void run() {
      synchronized (Blocked.class) {
        while (true) {
          SleepUtils.second(100);
        }
      }
    }
  }
}
