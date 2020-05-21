package com.zhang.demo.part14;

import com.google.common.collect.Lists;
import java.util.List;

class CountedInteger {

  private static long counter;
  private final long id = counter++;

  @Override
  public String toString() {
    return Long.toString(id);
  }
}

public class FilledList<T> {

  private Class<T> type;

  public FilledList(Class<T> type) {
    this.type = type;
  }

  public List<T> create(int nElements) {
    List<T> result = Lists.newArrayList();
    for (int i = 0; i < nElements; i++) {
      try {
        result.add(type.newInstance());
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  public static void main(String[] args) {
    FilledList<CountedInteger> filledList = new FilledList<>(CountedInteger.class);
    System.out.println(filledList.create(15));
  }
}
