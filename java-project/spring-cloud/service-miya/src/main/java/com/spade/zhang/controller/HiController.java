package com.spade.zhang.controller;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {

  private static final Logger logger = LoggerFactory.getLogger(HiController.class);

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/hi")
  public String home() {
    logger.info("hi is being called");
    return "hi i'm miya!";
  }

  @RequestMapping("/miya")
  public String info() {
    logger.info("miya is being called");
    return restTemplate.getForObject("http://localhost:8763/info", String.class);
  }

  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }
}
