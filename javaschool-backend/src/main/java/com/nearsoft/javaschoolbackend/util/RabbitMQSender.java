package com.nearsoft.javaschoolbackend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nearsoft.javaschoolbackend.model.request.TypeRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing-key}")
    private String routingkey;

    public String send(String type) {
        try {
            return (String)rabbitTemplate.convertSendAndReceive(exchange, routingkey, new TypeRequest(type).toJSONString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
