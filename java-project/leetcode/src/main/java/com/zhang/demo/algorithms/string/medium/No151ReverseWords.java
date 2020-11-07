package com.zhang.demo.algorithms.string.medium;

public class No151ReverseWords {

  public String reverseWords(String s) {

    String result = "";
    StringBuffer temp = new StringBuffer();
    boolean start = false;
    boolean first = true;
    for (int index = 0; index < s.length(); index++) {

      if (start) {
        if (s.charAt(index) != ' ') {
          temp.append(s.charAt(index));
        } else {

          start = false;

          if (!first) {
            first = false;
            temp.append(' ');
          }

          temp.append(result);
          result = temp.toString();
          temp = new StringBuffer();
        }
      } else {
        if (s.charAt(index) != ' ') {
          start = true;
          temp.append(s.charAt(index));
        }
      }
    }
    temp.append(' ');
    temp.append(result);
    result = temp.toString();

    return result;
  }
}
