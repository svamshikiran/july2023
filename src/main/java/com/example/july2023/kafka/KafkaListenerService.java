package com.example.july2023.kafka;

import com.example.july2023.model.Student;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerService {

    @KafkaListener(topics = "student.topic", groupId = "user_group")
    public void consumeUser1(ConsumerRecord<String, Student> consumerRecord) {

        System.out.println("CONSUMER RECORD: "+consumerRecord.value());
    }
}
