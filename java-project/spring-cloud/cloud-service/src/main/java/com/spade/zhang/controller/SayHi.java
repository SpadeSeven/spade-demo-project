package com.spade.zhang.controller;

import com.spade.zhang.model.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHi {

  private static final Logger logger = LoggerFactory.getLogger(SayHi.class);

  @Value("${spring.cloud.client.ipAddress}")
  private String ip;

  @RequestMapping("/hi")
  public String home(@RequestParam(value = "name", defaultValue = "wzhang.spade") String name) {
    try {
      Thread.sleep(1000 * 2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "hi,i am " + name + ", from: " + ip;
  }

  @PostMapping("/hiPost")
  public String hiPost(Param param) {
    logger.info("hiPost");
//    try {
//      Thread.sleep(1000 * 10);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    return "hi,i am " + param.getName() + ", from: " + ip;
  }

}
