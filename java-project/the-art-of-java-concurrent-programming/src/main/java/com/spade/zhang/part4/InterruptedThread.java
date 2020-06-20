package com.spade.zhang.part4;

import java.util.concurrent.TimeUnit;

/**
 * @author 93908
 */
public class InterruptedThread {

  public static void main(String[] args) throws InterruptedException {
    Thread sleepThread = new Thread(new SleepRunner(), "sleep runner");
    sleepThread.setDaemon(true);

    Thread busyThread = new Thread(new BusyRunner(), "busy runner");
    busyThread.setDaemon(true);

    sleepThread.start();
    busyThread.start();

    TimeUnit.SECONDS.sleep(5L);
    sleepThread.interrupt();
    busyThread.interrupt();

    System.out.println("sleepThread interrupted is " + sleepThread.isInterrupted());
    System.out.println("busyThread interrupted is " + busyThread.isInterrupted());
    SleepUtils.second(2L);
  }

  static class SleepRunner implements Runnable {

    @Override
    public void run() {
      SleepUtils.second(10L);
    }
  }

  static class BusyRunner implements Runnable {

    @Override
    public void run() {
      while (true) {
      }
    }
  }
}
