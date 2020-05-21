package com.zhang.demo.part14;

public class GenericToyDemo {

  public static void main(String[] args) throws IllegalAccessException, InstantiationException {
    Class<FencyToy> fencyToyClass = FencyToy.class;
    FencyToy fencyToy = fencyToyClass.newInstance();
    Class<? super FencyToy> up = fencyToyClass.getSuperclass();
    Object obj = up.newInstance();
  }
}
