package com.spade.zhang.model;

public class SendParam {

  /**
   * 主题
   */
  private String topic;

  /**
   * 键
   */
  private String key;

  /**
   * 值
   */
  private String value;

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("SendParam{");
    sb.append("topic='").append(topic).append('\'');
    sb.append(", key='").append(key).append('\'');
    sb.append(", value='").append(value).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
