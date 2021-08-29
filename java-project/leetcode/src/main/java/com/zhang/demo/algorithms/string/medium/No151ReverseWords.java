package com.zhang.demo.algorithms.string.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class No151ReverseWords {

  public String reverseWords(String s) {
    List<String> words = Arrays.asList(s.trim().split("\\s+"));
    Collections.reverse(words);
    return String.join(" ", words);
  }
}
