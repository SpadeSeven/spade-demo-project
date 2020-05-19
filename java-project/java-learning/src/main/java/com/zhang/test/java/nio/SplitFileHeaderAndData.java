package com.zhang.test.java.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class SplitFileHeaderAndData {

  public static void main(String[] args) throws IOException {
    File input = new File(
        SplitFileHeaderAndData.class.getResource("/data/test_841_file.txt").getFile());
    BufferedReader reader = new BufferedReader(new FileReader(input));
    CharBuffer charBuffer = CharBuffer.allocate(4096);

    File header = new File(
        SplitFileHeaderAndData.class.getResource("/data/test_841_file.header").getFile());
    File data = new File(
        SplitFileHeaderAndData.class.getResource("/data/test_841_file.data").getFile());
    BufferedWriter headerWriter = new BufferedWriter(new FileWriter(header));
    BufferedWriter dataWriter = new BufferedWriter(new FileWriter(data));
    boolean lastIsEnd = false;
    boolean isStartWriteData = false;
    while (reader.read(charBuffer) != -1) {
      charBuffer.flip();
      while (charBuffer.hasRemaining()) {
        char current = charBuffer.get();

        if (isStartWriteData) {
          dataWriter.write(current);
          continue;
        }

        if (lastIsEnd) {
          if ('\r' == current) {
            continue;
          } else if ('\n' == current) {
            isStartWriteData = true;
            continue;
          } else {
            lastIsEnd = false;
            headerWriter.write(current);

          }
        } else {
          headerWriter.write(current);
        }

      }
      charBuffer.clear();
    }

    reader.close();
    headerWriter.flush();
    dataWriter.flush();
    headerWriter.close();
    dataWriter.close();
  }

}
