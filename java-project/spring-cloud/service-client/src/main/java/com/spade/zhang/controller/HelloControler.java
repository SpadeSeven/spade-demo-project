package com.spade.zhang.controller;

import brave.sampler.Sampler;
import com.spade.zhang.service.HelloService;
import com.spade.zhang.service.HiPostService;
import com.spade.zhang.service.HiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

  private static final Logger logger = LoggerFactory.getLogger(HelloControler.class);

  @Autowired
  HelloService helloService;

  @Autowired
  private HiService hiService;

  @Autowired
  private HiPostService hiPostService;

  @GetMapping(value = "/hello")
  public String hello(@RequestParam String name) {
    logger.info("calling trace service-hi");
    return helloService.hiService(name);
  }

  @GetMapping(value = "/hiGet")
  public String hiGet(@RequestParam String name) {
    return hiPostService.hiGet(name);
  }

  @GetMapping(value = "/hiPost")
  public String hiPost(@RequestParam String name) {
    return hiPostService.hiPost(name);
  }

  @RequestMapping("/info")
  public String info() {
    logger.info("calling trace service-hi");
    return "i'm service-hi";
  }

  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }
}
