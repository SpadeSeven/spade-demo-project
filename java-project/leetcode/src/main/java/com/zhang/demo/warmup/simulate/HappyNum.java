package com.zhang.demo.warmup.simulate;

import java.util.HashSet;

/**
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为
 * 1，那么这个数就是快乐数。
 */
public class HappyNum {

  public boolean isHappy(int n) {

    int happyNum = n;
    HashSet<Integer> visited = new HashSet<>();
    while (happyNum != 1) {
      visited.add(happyNum);
      happyNum = calcHappyNum(happyNum);

      // 2、判断无限不循环
      if (visited.contains(happyNum)) {
        return false;
      }
    }

    return true;
  }

  public int calcHappyNum(int n) {
    int result = 0;

    // 1、获取每一位数字
    int num = 10;
    int mod = 0;
    while (n / num != 0) {
      mod = n % num;
      result += (mod * mod);

      n /= 10;
    }

    mod = n % num;
    result += (mod * mod);

    return result;
  }

}
