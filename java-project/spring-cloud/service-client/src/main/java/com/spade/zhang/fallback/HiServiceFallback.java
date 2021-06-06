package com.spade.zhang.fallback;

import com.spade.zhang.service.HiService;
import org.springframework.stereotype.Component;

@Component
public class HiServiceFallback implements HiService {

  @Override
  public String sayHiFromClientOne(String name) {
    return "sorry, hiservice error";
  }
}
