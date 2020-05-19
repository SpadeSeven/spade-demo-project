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

  public static void main(String[] args) {
    System.out.println("inside main");
    new Candy();
    System.out.println("after createing candy");
    try {
      Class.forName("com.zhang.demo.part14.Gum");
    } catch (ClassNotFoundException e) {
      System.out.println("could't finf Gum");
    }
    System.out.println("After Class.forname(\"com.zhang.demo.part14.Gum\")");
    new Cookie();
    System.out.println("after createing Cookie");
  }
}
