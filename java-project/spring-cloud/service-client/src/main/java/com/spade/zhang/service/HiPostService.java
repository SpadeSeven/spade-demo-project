package com.spade.zhang.service;

import com.spade.zhang.model.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HiPostService {

  private static final Logger logger = LoggerFactory.getLogger(HiPostService.class);

  @Autowired
  private RestTemplate restTemplate;

  public String hiGet(String name) {
    return restTemplate.getForObject("http://172.168.10.27/hi?name=" + name, String.class);
  }


  public String hiPost(String name) {
    Param param = new Param();
    param.setName(name);
    return restTemplate.postForObject("http://172.168.10.27/hiPost", param, String.class);
  }

}
