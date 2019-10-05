package com.nearsoft.project.rabbitmq.implementation;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqImpl implements Rabbitmq {


    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key")
    private String routingKey;

    public RabbitmqImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public String getMessage(String msg) {
        return (String) rabbitTemplate.convertSendAndReceive(exchange, routingKey, msg);
    }
}
