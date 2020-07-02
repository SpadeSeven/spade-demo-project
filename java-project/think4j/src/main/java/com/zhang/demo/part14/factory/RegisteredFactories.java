package com.zhang.demo.part14.factory;

import com.google.common.collect.Lists;
import java.util.List;

class Part {

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }


  static List<Factory<? extends Part>> partFactories = Lists.newArrayList();

  static {

  }

}


public class RegisteredFactories {

}
