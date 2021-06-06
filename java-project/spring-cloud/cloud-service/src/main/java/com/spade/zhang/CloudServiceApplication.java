package com.spade.zhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudServiceApplication.class, args);
  }

}
