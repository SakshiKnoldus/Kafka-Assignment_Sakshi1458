package com.knoldus.SerializationClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import java.util.Map;

public class StudentDataSerializer implements Serializer {
    @Override public void configure(Map map, boolean b) {
    }
    @Override public byte[] serialize(String arg0, Object arg1) {
        byte[] getValue = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            getValue = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getValue;
    }


    @Override public void close() {
    }
}
