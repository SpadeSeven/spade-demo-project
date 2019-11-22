package com.zhang.test.java.designpattern.strategy;

@MsgTypeHandler(value = MSG_TYPE.TEXT)
public class TextMessageService implements MessageService {

  @Override
  public void handleMessage(MessageInfo messageInfo) {
    System.out.println(String
        .format("处理文本消息: %s", messageInfo.getContent()));
  }
}
