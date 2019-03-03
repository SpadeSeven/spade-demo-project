package com.zhang.demo.part1.singleton;

/**
 * Created by Administrator on 2019-02-26.
 */
public enum SingletonF {
  INSTANCE;

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
