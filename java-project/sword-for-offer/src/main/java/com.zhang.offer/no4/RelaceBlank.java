package com.zhang.offer.no4;

/**
 * Created by Administrator on 2019-04-02.
 *
 * <p>请实现一个函数，吧字符串中的每个空格替换成"%20"。
 *
 * <p>例如：输入"we are happy"，则输出"we%20are%happy"
 */
public class RelaceBlank {

  public String relaceBlank(String str) {
    if (str == null || "".equals(str)) {
      return str;
    }

    char[] values = str.toCharArray();

    int blank_nums = 0;
    for (char ch : values) {
      if (' ' == ch) {
        blank_nums += 1;
      }
    }

    // 字符串长度
    int i = values.length - 1;
    // 替换结束后的字符串长度
    int j = values.length + blank_nums * 2 - 1;

    char[] target = new char[j + 1];

    while (i >= 0) {
      if (values[i] != ' ') {
        target[j--] = values[i--];
      } else {
        target[j--] = '0';
        target[j--] = '2';
        target[j--] = '%';
        i--;
      }
    }

    return new String(target);
  }
}
