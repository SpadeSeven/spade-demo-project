package com.spade.spring.no1.no1_1.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {
  public static void main(String[] args) {
    //
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("META-INF/spring/minstrel.xml");
    Knight knight = context.getBean(Knight.class);
    knight.embarkOnQuest();
    context.close();
  }
}
