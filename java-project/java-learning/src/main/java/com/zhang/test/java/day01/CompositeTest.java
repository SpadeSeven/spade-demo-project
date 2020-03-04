package com.zhang.test.java.day01;

/** Created by Administrator on 2018/5/27 0027. */
class Animal {
  private void beat() {
    System.out.println("心脏跳动...");
  }

  public void breath() {
    beat();
    System.out.println("吸一口气,吐一口气,呼吸中...");
  }
}

class Bird {
  private Animal a;

  public Bird(Animal a) {
    this.a = a;
  }

  public void breath() {
    a.breath();
  }

  public void fly() {
    System.out.println("我在天空自在的飞翔...");
  }
}

class Wolf {
  private Animal a;

  public Wolf(Animal a) {
    this.a = a;
  }

  public void breath() {
    a.breath();
  }

  public void run() {
    System.out.println("我在陆地上的快速奔跑...");
  }
}

public class CompositeTest {
  public static void main(String[] args) {
    Animal a1 = new Animal();
    Bird b = new Bird(a1);
    b.breath();
    b.fly();

    Animal a2 = new Animal();
    Wolf w = new Wolf(a2);
    w.breath();
    w.run();
  }
}
