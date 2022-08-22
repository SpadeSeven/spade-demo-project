package com.spade.zhang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class No3 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    Map<String, List<String>> map = new HashMap<>();
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] fields = line.split(" ");
      if (fields.length != 2){
        break;
      }
      String a = fields[0];
      String b= fields[1];

      if (map.containsKey(a)) {
        map.get(a).add(b);
      } else {
        List<String> list = new ArrayList<>();
        list.add(b);
        map.put(a, list);
      }
    }
    System.out.println("");
  }
}
