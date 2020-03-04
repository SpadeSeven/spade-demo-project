package com.zhang.test.java.day07;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

/** Created by Administrator on 2018/6/3 0003. */
public class SystemTest {
  public static void main(String[] args) throws Exception {
    Map<String, String> env = System.getenv();
    for (String name : env.keySet()) {
      System.out.println(name + " ---> " + env.get(name));
    }
    System.out.println(System.getenv("JAVA_HOME"));
    System.out.println("===============");
    Properties properties = System.getProperties();
    properties.store(new FileOutputStream("prop.txt"), "System Properties");
    System.out.println("===============");
    System.out.println(System.getProperty("os.name"));
  }
}
