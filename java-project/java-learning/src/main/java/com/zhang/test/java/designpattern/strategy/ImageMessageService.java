package com.zhang.test.java.designpattern.strategy;

@MsgTypeHandler(value = MSG_TYPE.IMAGE)
public class ImageMessageService implements MessageService {

  @Override
  public void handleMessage(MessageInfo messageInfo) {
    System.out.println(String.format("处理图片信息: %s", messageInfo.getContent()));
  }
}
