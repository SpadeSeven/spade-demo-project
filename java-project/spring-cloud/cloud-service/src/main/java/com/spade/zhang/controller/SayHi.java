package com.spade.zhang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHi {

  @RequestMapping("/hi")
  public String home(@RequestParam(value = "name", defaultValue = "wzhang.spade") String name) {
    return "hi,i am " + name;
  }

}
