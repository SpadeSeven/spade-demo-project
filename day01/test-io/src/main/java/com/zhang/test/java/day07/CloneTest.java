package com.zhang.test.java.day07;

/**
 * Created by Administrator on 2018/6/3 0003.
 */

class Address {
    String detail;

    public Address(String detail) {
        this.detail = detail;
    }
}

class User implements Cloneable {
    int age;
    Address address;

    public User(int age) {
        this.age = age;
        address = new Address("江苏南京");
    }

    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}

public class CloneTest {
    public static void main(String[] args) {
        User u1 = new User(23);
        User u2 = new User(25);
        System.out.println(u1 == u2);
        System.out.println(u1.address==u2.address);
    }
}
