package com.spade.zhang.controller;

import com.spade.zhang.service.HelloService;
import com.spade.zhang.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

  @Autowired
  HelloService helloService;

  @Autowired
  private HiService hiService;

  @GetMapping(value = "/hello")
  public String hello(@RequestParam String name) {
    return helloService.hiService(name);
  }

  @GetMapping(value = "/hi")
  public String hi(@RequestParam String name) {
    return hiService.sayHiFromClientOne(name);
  }
}
