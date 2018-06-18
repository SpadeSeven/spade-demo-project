package com.zhang.test.java.day01;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

class Father {
    public int age = 50;

    public void run() {
        System.out.println("father running");
    }
}

class Son extends Father {
    public int age = 20;

    public void run() {
        System.out.println("son running");
    }
}

public class Polymorphism {
    public static void main(String[] args) {
        Father person = new Son();
        System.out.println(person.getClass());
        System.out.println("age : " + person.age);
        person.run();
    }
}
