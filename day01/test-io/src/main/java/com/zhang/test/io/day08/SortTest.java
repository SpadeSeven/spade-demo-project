package com.zhang.test.java.day08;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Administrator on 2018/6/3 0003.
 */
public class SortTest {
    public static void main(String[] args) {
        ArrayList nums = new ArrayList();
        nums.add(2);
        nums.add(-5);
        nums.add(3);
        nums.add(0);
        System.out.println(nums);
        Collections.reverse(nums);
        System.out.println(nums);
        Collections.shuffle(nums);
        System.out.println(nums);

    }
}
