package com.spade.zhang.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;

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

  public boolean sendOnBack(String topic, String key, String value) {
    final boolean[] success = {true};
    kafkaTemplate.send(topic, key, value).addCallback(new SuccessCallback() {
      @Override
      public void onSuccess(Object o) {
        logger.info("成功");
      }
    }, new FailureCallback() {
      @Override
      public void onFailure(Throwable throwable) {
        logger.error("发送失败", throwable);
        success[0] = false;
      }
    });

    return success[0];
  }

}
