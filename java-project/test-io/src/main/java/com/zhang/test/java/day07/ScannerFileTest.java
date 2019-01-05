package com.zhang.test.java.day07;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/6/3 0003.
 */
public class ScannerFileTest {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("H:\\codeForLearn\\java\\day01\\test-java\\src\\main\\java\\com\\zhang\\test\\java\\day07\\ScannerFileTest.java"));
        System.out.println("ScannerFileTest.java文件的内容如下: ");
        while (scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }
}
