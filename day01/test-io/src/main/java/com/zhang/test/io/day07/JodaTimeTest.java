package com.zhang.test.java.day07;

import org.joda.time.DateTime;

/**
 * Created by Administrator on 2018/6/3 0003.
 */
public class JodaTimeTest {
    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);
        System.out.println(dateTime.toString("yyyy-MM-dd"));
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
    }
}
