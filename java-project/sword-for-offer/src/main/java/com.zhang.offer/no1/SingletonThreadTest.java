package com.zhang.offer.no1;

/**
 * Created by Administrator on 2019-04-01.
 */
public class SingletonThreadTest extends Thread {

  @Override
  public void run() {
    System.out.println("SingletonA: " + SingletonA.getInstance().hashCode());
//    System.out.println("SingletonB: " + SingletonB.getInstance().hashCode());
//    System.out.println("SingletonC: " + SingletonC.getInstance().hashCode());
//    System.out.println("SingletonD: " + SingletonD.getInstance().hashCode());
//    System.out.println("SingletonE: " + SingletonE.getInstance().hashCode());
//    System.out.println("SingletonF: " + SingletonF.INSTANCE.hashCode());
//    System.out.println("SingletonG: " + SingletonG.getInstance().hashCode());

  }

  public static void main(String[] args) {
    int threadnums = 100;
    SingletonThreadTest[] arr = new SingletonThreadTest[threadnums];
    for (int i = 0; i < threadnums; i++) {
      arr[i] = new SingletonThreadTest();
    }

    for (int j = 0; j < threadnums; j++) {
      arr[j].start();
    }
  }
}
