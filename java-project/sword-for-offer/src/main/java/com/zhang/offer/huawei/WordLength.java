package com.zhang.offer.huawei;

public class WordLength {
  public int wordLength(String str) {
    String[] fields = str.split(" ");
    return fields[fields.length - 1].toCharArray().length;
  }
}
