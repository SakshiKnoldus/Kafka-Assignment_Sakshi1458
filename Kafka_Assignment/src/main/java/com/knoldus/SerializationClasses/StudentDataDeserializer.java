package com.knoldus.SerializationClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.StudentModel.StudentDetailModel;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class StudentDataDeserializer implements Deserializer {
    @Override public void close() {
    }
    @Override public void configure(Map arg0, boolean arg1) {
    }
    @Override
    public StudentDetailModel deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        StudentDetailModel studentDetail = null;
        try {
            studentDetail = mapper.readValue(arg1, StudentDetailModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentDetail;
    }
}