package com.zhang.demo.part14;

interface HasBatteries {

}

interface Waterproof {

}

interface Shoots {

}

interface Colorful {

}

class Toy {

  public Toy() {
  }

  public Toy(int i) {
  }
}

class FencyToy extends Toy implements HasBatteries, Waterproof, Shoots, Colorful {

  FencyToy() {
    super(1);
  }
}

public class ToyTest {

  static void printInfo(Class cc) {
    System.out.println("Class name:" + cc.getName() + "is interface? [" + cc.isInterface() + "]");
    System.out.println("Simple name: " + cc.getSimpleName());
    System.out.println("canonical name: " + cc.getCanonicalName());
  }

  public static void main(String[] args) {
    Class cc = null;
    try {
      cc = Class.forName("com.zhang.demo.part14.FencyToy");
    } catch (ClassNotFoundException e) {
      System.out.println("Can't find FencyToy");
      System.exit(1);
    }
    printInfo(cc);
    for (Class face : cc.getInterfaces()) {
      printInfo(face);
    }

    Class up = cc.getSuperclass();
    Object obj = null;
    try {
      obj = up.newInstance();
    } catch (InstantiationException e) {
      System.out.println("can't instantiate");
      System.exit(1);
    } catch (IllegalAccessException e) {
      System.out.println("can't access");
      System.exit(1);
    }
    printInfo(obj.getClass());
  }

}
