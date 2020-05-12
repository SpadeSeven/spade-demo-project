package com.spade.zhang.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {

  public static void main(String[] args) {
    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers", "172.168.10.10:6667");
    properties.setProperty("key.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");
    properties.setProperty("value.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");
    // 消费群组
    properties.setProperty("group.id", "test.consumers");

    Consumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
    // 订阅topic
    consumer.subscribe(Collections.singletonList("test.kafka.topic01"));

    // 轮询消息

    try {
      while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
        for (ConsumerRecord<String, String> record : records) {
          System.out.println(record.value());
        }
      }
    } finally {
      consumer.close();
    }

  }

}
