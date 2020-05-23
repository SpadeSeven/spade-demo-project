package com.zhang.demo.part14.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {

  private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();

  private static String[] typeNames = {
      "com.zhang.demo.part14.pets.Mutt",
      "com.zhang.demo.part14.pets.Pug",
      "com.zhang.demo.part14.pets.EgyptianMau",
      "com.zhang.demo.part14.pets.Manx",
      "com.zhang.demo.part14.pets.Cymrix",
      "com.zhang.demo.part14.pets.Rat",
      "com.zhang.demo.part14.pets.Mouse",
      "com.zhang.demo.part14.pets.Hamster"};

  private static void loader() {
    for (String typeName : typeNames) {
      try {
        types.add((Class<? extends Pet>) Class.forName(typeName));
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  static {
    loader();
  }

  @Override
  public List<Class<? extends Pet>> types() {
    return types;
  }
}
