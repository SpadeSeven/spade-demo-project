package com.zhang.offer.wind;

import java.util.concurrent.atomic.AtomicInteger;

public class CheeseProduct {

  /** 运输量 */
  private static final int TRANSPORT_SIZE = 100;

  /** 仓库量 */
  private static final int WAREHOUSE_CAPACITY = 1000;

  /** 牛奶量 */
  private static final int CHEESE_CAPACITY = 100000;

  private static AtomicInteger cheese_size = new AtomicInteger(0);

  private static AtomicInteger milk_size = new AtomicInteger(0);

  private static AtomicInteger starter_size = new AtomicInteger(0);

  private static AtomicInteger warehouse_size = new AtomicInteger(0);

  public static void main(String[] args) {
    Thread milk = new Thread(new MilkProductionLine());
    Thread starter = new Thread(new StarterProductionLine());
    Thread cheese = new Thread(new CheeseProductionLine());
    Thread car = new Thread(new Car());

    milk.start();
    starter.start();
    cheese.start();
    car.start();
  }

  /** 牛奶生产线 */
  static class MilkProductionLine implements Runnable {

    @Override
    public void run() {
      for (; ; ) {
        if (milk_size.get() < CHEESE_CAPACITY * 2) {
          milk_size.getAndAdd(2);
          System.out.println("生产牛奶。。。");
        } else {
          break;
        }
      }
      System.out.println("结束生产牛奶");
    }
  }

  /** 发酵剂生产线 */
  static class StarterProductionLine implements Runnable {

    @Override
    public void run() {
      for (; ; ) {
        if (starter_size.get() < CHEESE_CAPACITY) {
          starter_size.getAndAdd(1);
          System.out.println("生产发酵剂。。。");
        } else {
          break;
        }
      }
      System.out.println("结束生产发酵剂");
    }
  }

  /** 奶酪生产线 */
  static class CheeseProductionLine implements Runnable {

    @Override
    public void run() {
      while (cheese_size.get() < CHEESE_CAPACITY) {

        if (cheese_size.get() * 2 == milk_size.get() && cheese_size.get() == starter_size.get()) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        if (warehouse_size.get() < WAREHOUSE_CAPACITY) {
          cheese_size.getAndAdd(1);
          warehouse_size.getAndAdd(1);
          System.out.println("生产奶酪。。。");
        }
      }
      System.out.println("结束生产奶酪");
    }
  }

  /** 奶酪生产线 */
  static class Car implements Runnable {

    @Override
    public void run() {
      for (; ; ) {
        if (warehouse_size.get() >= TRANSPORT_SIZE) {
          warehouse_size.getAndAdd(0 - TRANSPORT_SIZE);
          System.out.println("运送。。。。");
        }

        if (cheese_size.get() == CHEESE_CAPACITY && warehouse_size.get() == 0) {
          break;
        }
      }
      System.out.println("结束运送");
    }
  }
}
