package com.zhang.test.java.day02.person;

/**
 * Created by Administrator on 2018/5/27 0027.
 */
public class Student extends Person{

    public Student(){
        this.setProfession("student");
    }

    public void study(){
        System.out.println("学习...");
    }
}
