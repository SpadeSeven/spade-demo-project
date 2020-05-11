package com.spade.zhang.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SimpleProducer {

  public static void main(String[] args) {
    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers", "172.168.10.10:6667");
    properties.setProperty("key.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");
    properties.setProperty("value.serializer",
        "org.apache.kafka.common.serialization.StringSerializer");

    Producer<String, String> producer = new KafkaProducer<String, String>(properties);

    ProducerRecord<String, String> record = new ProducerRecord<>("test.kafka.topic01", "04",
        "04,13700001234,20200511011301");

    SimpleProducer produce = new SimpleProducer();
    //produce.syncSend(producer, record);
    produce.asyncSend(producer, record);
  }


  private void syncSend(Producer producer, ProducerRecord record) {
    try {
      producer.send(record).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void asyncSend(Producer producer, ProducerRecord record) {
    producer.send(record, new ProducerCallBack());
  }
}

class ProducerCallBack implements Callback {

  @Override
  public void onCompletion(RecordMetadata metadata, Exception exception) {
    System.out.println(metadata.toString());
    if (exception != null) {
      exception.getMessage();
    }
  }
}
