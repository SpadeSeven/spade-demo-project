package com.zhang.test.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BasicChannelExample {

  public static void main(String[] args) throws IOException {
    RandomAccessFile file = new RandomAccessFile("prop.txt", "rw");
    FileChannel channel = file.getChannel();

    // create buffer with capacity of 48 bytes
    ByteBuffer byteBuffer = ByteBuffer.allocate(48);

    // read into buffer
    int byteRead = 0;
    while ((byteRead = channel.read(byteBuffer)) != -1) {
      // make buffer ready for reading
      byteBuffer.flip();

      while (byteBuffer.hasRemaining()) {
        // read 1 byte at a time
        System.out.println((char) byteBuffer.get());
      }

      // clean buffer, make buffer ready for writing
      byteBuffer.clear();
    }
    file.close();
  }
}
