package com.spade.spring.no1.no1_1.di.config;

import com.spade.spring.no1.no1_1.di.BraveKnight;
import com.spade.spring.no1.no1_1.di.Knight;
import com.spade.spring.no1.no1_1.di.Quest;
import com.spade.spring.no1.no1_1.di.SlayDragonQuest;
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
