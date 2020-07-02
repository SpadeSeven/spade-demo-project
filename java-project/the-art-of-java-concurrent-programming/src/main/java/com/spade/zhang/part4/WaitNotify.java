package com.spade.zhang.part4;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author spade
 */
public class WaitNotify {

  static boolean flag = true;
  static Object lock = new Object();

  static class Wait implements Runnable {

    @Override
    public void run() {
      synchronized (lock) {
        while (flag) {
          try {
            System.out.println(
                Thread.currentThread() + " flag is true. wait@ " + new SimpleDateFormat("HH:mm:ss")
                    .format(new Date()));
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println(
            Thread.currentThread() + " flag is false. wait@ " + new SimpleDateFormat("HH:mm:ss")
                .format(new Date()));
      }
    }
  }

  static class Notify implements Runnable {

    @Override
    public void run() {
      synchronized (lock) {

      }
    }
  }


}
