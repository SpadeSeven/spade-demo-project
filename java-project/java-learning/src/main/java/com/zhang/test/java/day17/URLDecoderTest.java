package com.zhang.test.java.day17;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/** Created by Administrator on 2018/6/4 0004. */
public class URLDecoderTest {

  public static void main(String[] args) throws UnsupportedEncodingException {
    String keyword = URLDecoder.decode("%E5%BC%A0%E4%BC%9F", "utf-8");
    System.out.println(keyword);

    System.out.println(URLEncoder.encode("张伟", "utf-8"));
  }
}
