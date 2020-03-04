package com.zhang.test.java.day02.person;

/** Created by Administrator on 2018/5/27 0027. */
public class Person {
  public int age;

  public String profession;

  public void eat() {
    System.out.println("吃饭");
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }
}
