package com.spade.zhang.nacosdemo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("config")
public class ConfigController {

  @NacosValue(value = "${server.name:name}", autoRefreshed = true)
  private String name;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  @ResponseBody
  public String get() {
    return name;
  }
}
