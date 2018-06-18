package com.zhang.test.java.day07;

/**
 * Created by Administrator on 2018/6/3 0003.
 */
public class IdentityHashCodeTest {
    public static void main(String[] args) {
        String s1 = new String("Hello");
        String s2 = new String("Hello");

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        System.out.println("identity hashcode");

        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));

        String s3 = "java";
        String s4 = "java";
        System.out.println("==============");
        System.out.println(System.identityHashCode(s3));
        System.out.println(System.identityHashCode(s4));
    }
}
