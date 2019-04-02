package com.zhang.offer.no4;

/**
 * Created by Administrator on 2019-04-02.
 *
 * 请实现一个函数，吧字符串中的每个空格替换成"%20"。
 *
 * 例如：输入"we are happy"，则输出"we%20are%happy"
 */
public class RelaceBlank {

  public static void main(String[] args) {
    RelaceBlank rb = new RelaceBlank();
    // 字符串在最前面
    System.out.println(rb.relaceBlank(" wearehappy"));
    // 字符串在中间
    System.out.println(rb.relaceBlank("we are happy"));
    // 字符串在最后面
    System.out.println(rb.relaceBlank("wearehappy "));
    // 连续空格
    System.out.println(rb.relaceBlank("we  are  happy"));
    // 没有空格
    System.out.println(rb.relaceBlank("wearehappy"));
    // null
    System.out.println(rb.relaceBlank(null));
    // 空字符串
    System.out.println(rb.relaceBlank(""));
    // 只有一个空字符串
    System.out.println(rb.relaceBlank(" "));
    // 只有空的连续多个字符串
    System.out.println(rb.relaceBlank("  "));

  }

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
