package com.spade.zhang.controller;

import com.spade.zhang.model.SendParam;
import com.spade.zhang.producer.KafkaSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

  @Autowired
  private KafkaSend kafkaSend;

  @PostMapping(value = "/send")
  public String send(@RequestBody SendParam param) {
    boolean success = kafkaSend.send(param.getTopic(), param.getKey(), param.getValue());
    return String.valueOf(success);
  }

  @RequestMapping(value = "/send1")
  public String send1(@RequestBody SendParam param) {
    boolean success = kafkaSend.sendOnBack(param.getTopic(), param.getKey(), param.getValue());
    return String.valueOf(success);
  }
}
