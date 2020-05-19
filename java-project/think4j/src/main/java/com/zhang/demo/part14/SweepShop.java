package com.zhang.demo.part14;

class Candy {

  static {
    System.out.println("loading candy ...");
  }
}

class Gum {

  static {
    System.out.println("loading gum ...");
  }
}

class Cookie {

  static {
    System.out.println("loading cookie ...");
  }
}

public class SweepShop {

  public static void main(String[] args) throws ClassNotFoundException {
    SweepShop sweepShop = new SweepShop();
    String packageName =sweepShop.getClass().getPackage().getName();
    for (String arg :args){
      Class.forName(packageName + "." + arg);
    }
  }
}
