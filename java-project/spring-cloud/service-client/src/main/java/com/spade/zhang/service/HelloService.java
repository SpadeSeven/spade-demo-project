package com.spade.zhang.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

  private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "hiError")
  public String hiService(String name) {
    return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
  }

  public String hiError(String name) {
    return "hi,"+name+",sorry,error!";
  }

}
