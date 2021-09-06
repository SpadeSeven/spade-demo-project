package com.spade.zhang.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spade.zhang.model.Param;
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
  @Autowired
  private RestTemplate restTemplateLoadBalanced;

  @HystrixCommand(fallbackMethod = "hiError")
  public String hiService(String name) {
    return restTemplateLoadBalanced.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
  }


  public String hiGet(String name) {
    return restTemplate.getForObject("http://172.168.10.27/hi?name=" + name, String.class);
  }


  public String hiPost(String name) {
    Param param = new Param();
    param.setName(name);
    return restTemplate.postForObject("http://172.168.10.27/hiPost", param, String.class);
  }

  public String hiError(String name) {
    return "hi," + name + ",sorry,error!";
  }

}
