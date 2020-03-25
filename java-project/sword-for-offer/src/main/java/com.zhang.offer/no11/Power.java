package com.zhang.offer.no11;

/**
 * Created by Administrator on 2019-05-30.
 *
 * <p>实现函数double Power(double base,int exponent)
 *
 * <p>求base的exponent次方，不得使用库函数，同时不需要考虑大数问题
 */
public class Power {

  public static void main(String[] args) {
    Power power = new Power();
    // System.out.println(power.power(0.0, 0));
    System.out.println(power.power(2, 2));
    System.out.println(power.power(2, -2));
  }

  public double power(double base, int exponent) {
    // 基数和指数不能同时为0
    if (equal(base, 0.0) && exponent == 0) {
      throw new RuntimeException("invalid input , Both the base and the exponent are zore");
    }

    // 指数为0，直接返回
    if (exponent == 0) {
      return 1.0;
    }

    // 指数的绝对值
    int abs_exp = exponent;
    if (exponent < 0) {
      abs_exp = -exponent;
    }

    double result = powerWithUnsignedExponent(base, abs_exp);

    // 指数为负数
    if (exponent < 0) {
      result = 1.0 / result;
    }

    return result;
  }

  /**
   * 计算一个正数的N次方
   *
   * @param base 基数
   * @param exponent 次方数
   * @return 结果值
   */
  public double powerWithUnsignedExponent(double base, int exponent) {
    if (exponent == 0) {
      return 1.0;
    } else if (exponent == 1) {
      return base;
    }
    double result = powerWithUnsignedExponent(base, exponent >> 1);
    result *= result;
    if ((exponent & 1) == 1) {
      result *= base;
    }

    return result;
  }

  /** 判断两个double类型的值是否相等 */
  public boolean equal(double num1, double num2) {
    if (num1 - num2 < 0.000001 && num1 - num2 > -0.000001) {
      return true;
    }
    return false;
  }
}
