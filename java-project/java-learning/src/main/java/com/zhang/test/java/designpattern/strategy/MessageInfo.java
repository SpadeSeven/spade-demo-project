package com.zhang.test.java.designpattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageInfo {

  private Integer type;

  private String content;
}
