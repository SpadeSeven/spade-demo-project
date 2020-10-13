package com.zhang.test.java.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleDateFormatDemo {

  public static void main(String[] args) throws InterruptedException {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ThreadLocal<DateFormat> ts = ThreadLocal.withInitial(() -> df);


    ExecutorService executors = Executors.newFixedThreadPool(1000);
    for (; ; ) {
      executors.execute(new Runnable() {
        @Override
        public void run() {
          try {
            String format = ts.get().format(new Date(Math.abs(new Random().nextLong())));
            System.out.println(format);
          } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
          }
        }
      });
    }


  }
}

