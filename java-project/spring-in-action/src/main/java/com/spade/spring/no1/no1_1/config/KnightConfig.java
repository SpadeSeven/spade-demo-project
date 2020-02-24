package com.spade.spring.no1.no1_1.config;

import com.spade.spring.no1.no1_1.BraveKnight;
import com.spade.spring.no1.no1_1.Knight;
import com.spade.spring.no1.no1_1.Quest;
import com.spade.spring.no1.no1_1.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KnightConfig {

  @Bean
  public Knight knight() {
    return new BraveKnight(quest());
  }

  @Bean
  public Quest quest() {
    return new SlayDragonQuest(System.out);
  }
}
