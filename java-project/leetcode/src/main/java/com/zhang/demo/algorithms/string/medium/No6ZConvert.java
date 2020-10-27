package com.zhang.demo.algorithms.string.medium;

public class No6ZConvert {

  public String convert(String s, int numRows) {

    if (numRows == 1) {
      return s;
    }

    int rows = numRows;
    int cols = calcCols(s.length(), numRows);

    char[][] chars = new char[rows][cols];
    for (int index = 1; index <= s.length(); index++) {
      int col = calcCols(index, numRows);
      int row = calcRow(index, rows);
      chars[row - 1][col - 1] = s.charAt(index - 1);
    }

    StringBuffer sb = new StringBuffer();
    for (int row = 0; row < chars.length; row++) {
      for (int col = 0; col < chars[row].length; col++) {
        if (chars[row][col] != '\u0000') {
          sb.append(chars[row][col]);
        }
      }
    }

    return sb.toString();
  }

  // 计算列
  int calcCols(int length, int numrows) {

    // 确定组
    // 组
    int a = length / (2 * numrows - 2);
    // 组的余数
    int b = length % (2 * numrows - 2);

    // 确定组里的第几列
    if (b != 0 && b <= numrows) {
      return a * (numrows - 1) + 1;
    } else if (b != 0) {
      return a * (numrows - 1) + (b - numrows) + 1;
    }

    return a * (numrows - 1);
  }

  // 计算行
  int calcRow(int length, int numrows) {
    // 组的余数
    int b = length % (2 * numrows - 2);

    // 确定组里的第几列
    if (b != 0 && b <= numrows) {
      return b;
    } else if (b != 0) {
      return 2 * numrows - b;
    }

    return 2;
  }
}
