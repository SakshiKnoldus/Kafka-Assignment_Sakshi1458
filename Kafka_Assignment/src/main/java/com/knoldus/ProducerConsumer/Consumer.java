package com.knoldus.ProducerConsumer;


import com.knoldus.StudentModel.StudentDetailModel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        ConsumerListener consumerListener= new ConsumerListener();
        Thread thread = new Thread(consumerListener);
        thread.start();
    }
    public static void consumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.knoldus.SerializationClasses.StudentDataDeserializer");
        properties.put("group.id", "test-group");

        KafkaConsumer<String, StudentDetailModel> kafkaConsumer = new KafkaConsumer(properties);
        List topics = new ArrayList();
        topics.add("Student");
        kafkaConsumer.subscribe(topics);
        try{
            ObjectMapper mapper = new ObjectMapper();

           FileWriter file = new FileWriter("StudentData.txt",true);
            while (true){
                ConsumerRecords<String, StudentDetailModel> records = kafkaConsumer.poll(Duration.ofSeconds(1000));

                for (ConsumerRecord<String, StudentDetailModel> record: records){
                    System.out.println(String.format("Topic - %s, Partition - %d, Value: %s", record.topic(), record.partition(), record.value().toString()));
                    String content = mapper.writeValueAsString(record.value());
                    file.append(content);
                    file.append("\n");
                }
                file.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            kafkaConsumer.close();
        }
    }
}

class ConsumerListener implements Runnable {


    @Override
    public void run() {
        Consumer.consumer();
    }
}