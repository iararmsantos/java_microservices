package com.iara.rabbittest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTestMessage() {
        rabbitTemplate.convertAndSend("zipkin", "Test message to RabbitMQ");
    }
}
