package com.spade.demo.part2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * <p>
 * jdk1.8不会抛出异常
 */
@Deprecated
public class RuntimeConstantPoolOOM {


  public static void main(String[] args) {
    List<String> list = new ArrayList<String>();
    int i = 0;
    while (true) {
      list.add(String.valueOf(i++).intern());
    }
  }

}
