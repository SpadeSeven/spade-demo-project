package com.zhang.demo.part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2019-02-26.
 */
public class TryWith {

  public static void main(String[] args) {
    TryWith tryWith = new TryWith();
  }

  public void test_1() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(""));
    try {
      br.readLine();
    } finally {
      br.close();
    }
  }

  public void test_2() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(""))) {
      br.readLine();
    }
  }
}
