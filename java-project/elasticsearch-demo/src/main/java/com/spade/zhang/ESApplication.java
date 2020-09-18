package com.spade.zhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ESApplication {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(ESApplication.class);
    application.run(args);
    application.setWebApplicationType(WebApplicationType.NONE);

    System.out.println("exit");
  }

}
