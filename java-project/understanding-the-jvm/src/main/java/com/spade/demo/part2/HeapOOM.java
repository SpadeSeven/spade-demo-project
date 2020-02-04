package com.spade.demo.part2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 * <p>
 * 将堆的最大及最小值都设置为20M，防止其自动扩展大小
 */
public class HeapOOM {

  static class OOMObject {

  }

  public static void main(String[] args) {
    List<OOMObject> list = new ArrayList<OOMObject>();

    while (true) {
      list.add(new OOMObject());
    }
  }

}
