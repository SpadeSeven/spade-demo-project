package com.zhang.geekbang.array;

import java.util.PrimitiveIterator;
import javax.sound.midi.Soundbank;

/**
 * Created by Administrator on 2019-02-19.
 */
public class Array {

  // 保存数据
  public int[] data;

  // 数组长度
  private int length;

  // 实际数据个数
  private int count;

  public Array(int capacity) {
    this.data = new int[capacity];
    this.length = capacity;
    this.count = 0;
  }

  /**
   * 根据索引查询数据
   *
   * @param index 索引
   * @return 值
   */
  public int find(int index) {
    if (index > count || index < 0) {
      return -1;
    }
    return data[index];
  }

  /**
   * 插入元素
   *
   * @param index 索引值
   * @param value 数据值
   * @return 是否成功
   */
  public boolean insert(int index, int value) {

    // 数组空间已满
    if (count == length) {
      System.out.println("array is full");
      return false;
    }

    // 索引不合法
    if (index < 0 || index > length) {
      System.out.println("index is illegal");
      return false;
    }

    // 索引值之后的数据全部后移一位
    for (int i = count; i < index; i--) {
      data[i] = data[i - 1];
    }
    data[index] = value;
    count += 1;

    return true;
  }

  /**
   * 根据索引值删除数据
   *
   * @param index 索引值
   * @return 成功与否
   */
  public boolean delete(int index) {

    // 索引不合法
    if (index < 0 || index >= count) {
      System.out.println("index is illegal");
      return false;
    }

    //从删除位置开始，将后面的元素向前移动一位
    for (int i = index; i < count; i++) {
      data[index] = data[index + 1];
    }

    return true;
  }

  public static void main(String[] args) {
    int[] arr = new int[3];
    arr[0] = 1;
    System.out.println(arr[1]);
  }

}
