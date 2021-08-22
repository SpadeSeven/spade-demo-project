package com.spade.zhang.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSend {

  private static final Logger logger = LoggerFactory.getLogger(KafkaSend.class);

  @Autowired
  private KafkaTemplate kafkaTemplate;

  public boolean send(String topic, String key, String value) {
    boolean success = true;
    try {
      kafkaTemplate.send(topic, key, value).get(10, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      logger.error("内部错误", e);
      success = false;
    } catch (ExecutionException e) {
      logger.error("执行错误", e);
      success = false;
    } catch (TimeoutException e) {
      logger.error("超时", e);
      success = false;
    } catch (Exception e) {
      logger.error("其他错误", e);
      success = false;
    }

    return success;


  }

}
