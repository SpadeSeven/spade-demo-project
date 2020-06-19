package com.spade.zhang.part4;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

  public static void second(long seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
