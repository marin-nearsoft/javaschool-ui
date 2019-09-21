package com.nearsoft.javaschoolbackend.util;

import com.nearsoft.javaschoolbackend.config.ConfigProperties;
import com.nearsoft.javaschoolbackend.exception.custom.CentralServerException;
import com.nearsoft.javaschoolbackend.model.request.TypeRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    private final ConfigProperties configProperties;

    public String send(String type) {
        try {
            return (String)rabbitTemplate.convertSendAndReceive(configProperties.getExchange(), configProperties.getRoutingKey(), new TypeRequest(type).toJSONString());
        } catch (Exception e) {
            throw new CentralServerException("An error occurred during communication with the central server. Requested type: "+type);
        }
    }

}
