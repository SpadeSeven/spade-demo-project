package com.zhang.demo.warmup.string;

/**
 * 验证回文串
 *
 * <p>给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * <p>说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * <p>示例 1: 输入: "A man, a plan, a canal: Panama" 输出: true
 *
 * <p>示例 2: 输入: "race a car" 输出: false
 */
public class IsPalindrome {

  public boolean isPalindrome(String s) {

    char[] chars = s.toCharArray();

    int count = 0;
    for (int i = 0; i < chars.length; i++) {
      if ((chars[i] >= '0' && chars[i] <= '9') || (chars[i] >= 'a' && chars[i] <= 'z')) {
        chars[count] = chars[i];
        count++;
      } else if ((chars[i] >= 'A' && chars[i] <= 'Z')) {
        chars[count] = (char) (chars[i] + 'a' - 'A');
        count++;
      }
    }

    int i = 0;
    while (i < count - 1) {
      if (chars[i] != chars[count - 1 - i]) {
        return false;
      }
      i++;
    }

    return true;
  }
}
