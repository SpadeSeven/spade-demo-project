package com.zhang.demo.part1;

import com.zhang.demo.part1.NyPizza.Size;
import com.zhang.demo.part1.Pizza.Topping;

/**
 * Created by Administrator on 2019-02-26.
 */
public class PizzaTest {

  public static void main(String[] args) {
    NyPizza nyPizza = new NyPizza.Builder(Size.SMALL).addTopping(Topping.SAUSAGE)
        .addTopping(Topping.ONION).build();

    Calzone calzone = new Calzone.Builder().addTopping(Topping.ONION).build();
  }
}
