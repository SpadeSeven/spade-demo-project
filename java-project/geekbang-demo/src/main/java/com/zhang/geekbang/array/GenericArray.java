package com.zhang.geekbang.array;

/**
 * Created by Administrator on 2019-02-19.
 */
public class GenericArray<T> {

  private T[] data;
  private int size;

  public GenericArray(int capacity) {
    this.data = (T[]) new Object[capacity];
    this.size = 0;
  }

  // 默认大小为10
  public GenericArray() {
    this(10);
  }

  // 获取数组容量
  public int getCapacity() {
    return data.length;
  }

  // 获取数组当前个数
  public int count() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  // 修改index位置的元素
  public void set(int index, T value) {
    checkIndex(index);
    data[index] = value;
  }

  // 根据索引查找数据
  public T get(int index) {
    checkIndex(index);
    return data[index];
  }

  // 查看数组是否包含元素
  public boolean contains(T value){
    for (int i = 0; i < size;i++){
      if (value.equals(data[i])){
        return true;
      }
    }
    return false;
  }




  private void checkIndex(int index) {
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("index is illegal,Require index >=0 and index <= size.");
    }
  }


}
