package com.spade.zhang;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class No2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();
    String[] fields = line.split(",");
    Map<Integer, Integer> map = new HashMap<>();

    for (int index = 0; index < fields.length; index++) {
      int value = Integer.parseInt(fields[index]);
      if (map.containsKey(value)) {
        map.put(value, map.get(value) + 1);
      } else {
        map.put(value, 1);
      }
    }

    TreeMap<Integer, List<Integer>> res = new TreeMap<>(new Comparator() {
      @Override
      public int compare(Object o1, Object o2) {
        int a = (int) o1;
        int b = (int) o2;
        return b - a;
      }
    });

    Set<Integer> processSet = new HashSet<>();
    for (int index = 0; index < fields.length; index++) {
      int value = Integer.parseInt(fields[index]);
      if (processSet.contains(value)) {
        continue;
      }

      int c = map.get(value);
      if (res.containsKey(c)) {
        res.get(c).add(value);
      } else {
        List<Integer> list = new ArrayList<>();
        list.add(value);
        res.put(c, list);
      }
      processSet.add(value);
    }

    List<Integer> list = new ArrayList<>();
    for (Entry<Integer, List<Integer>> entry : res.entrySet()) {
      list.addAll(entry.getValue());
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      if (i != list.size() - 1) {
        builder.append(list.get(i));
        builder.append(",");
      } else {
        builder.append(list.get(i));
      }
    }
    System.out.println(builder.toString());

  }
}
