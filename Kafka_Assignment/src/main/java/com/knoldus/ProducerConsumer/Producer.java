package com.knoldus.ProducerConsumer;

import com.knoldus.StudentModel.StudentDetailModel;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Random;

import java.util.Properties;


public class Producer {
    public static void main(String[] args){

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.knoldus.SerializationClasses.StudentDataSerializer");

        KafkaProducer<String, StudentDetailModel> kafkaProducer = new KafkaProducer<>(properties);
        try{
                Random rand = new Random();
            for(int i = 1; i <= 50; i++){
                StudentDetailModel studentDetail= new StudentDetailModel(i,"Sakshi", rand.nextInt(30), "MCA");

                kafkaProducer.send(new ProducerRecord<String,StudentDetailModel>("Student", Integer.toString(i),studentDetail));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }
}