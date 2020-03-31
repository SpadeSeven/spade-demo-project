package com.zhang.offer.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RandomNum {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    int size = scan.nextInt();
    Set<Integer> set = new HashSet<>(size);
    int count = 0;
    List<Integer> list = new ArrayList<>();
    while (scan.hasNextInt()) {
      if (count == size) {
        count = 0;
        list.addAll(set);
        size = scan.nextInt();
        set = new HashSet<>(size);
        continue;
      }
      int num = scan.nextInt();
      set.add(num);
      count++;
    }

    list.addAll(set);

    Object[] arr = list.toArray();
    Arrays.sort(arr);
    for (Object i : arr) {
      System.out.println(i);
    }
  }
}
