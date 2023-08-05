package com.example.july2023.kafka;

import com.example.july2023.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducerService {

    private static String topicName = "student.topic";

    @Autowired
    KafkaTemplate<String, Student> kafkaTemplate;

    public String sendSimpleMessage(Student student) throws JsonProcessingException {

        Message<Student> message = MessageBuilder
                .withPayload(student)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        ListenableFuture<SendResult<String, Student>> future = kafkaTemplate.send(message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Student>>() {
            public void onSuccess(SendResult<String, Student> result) {
                System.out.println("Sent message=[" + student.getRollno() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + student.getRollno() + "] due to : " + ex.getMessage());
            }
        });
        return "Success";
    }
}
