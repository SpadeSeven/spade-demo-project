package com.spade.zhang.part4;

public class Daemon {

  public static void main(String[] args) {

    Thread thread = new Thread(new DaemonRunner(), "daeamon-thread-1");
    thread.setDaemon(true);
    thread.start();

  }

  static class DaemonRunner implements Runnable {

    @Override
    public void run() {
      try {
        SleepUtils.second(10);
      } finally {
        System.out.println("Daemon thread finally");
      }
    }
  }
}
