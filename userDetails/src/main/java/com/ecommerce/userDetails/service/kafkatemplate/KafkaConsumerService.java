package com.ecommerce.userDetails.service.kafkatemplate;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "user-events", groupId = "user-service-group")
    public void consume(ConsumerRecord<String,String> record){
    log.info("Received kafka message: key={}, value={}",record.key(),record.value());
    }

}
