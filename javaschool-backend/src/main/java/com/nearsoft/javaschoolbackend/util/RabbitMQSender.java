package com.nearsoft.javaschoolbackend.util;

import com.nearsoft.javaschoolbackend.config.ConfigProperties;
import com.nearsoft.javaschoolbackend.exception.custom.CentralServerException;
import com.nearsoft.javaschoolbackend.model.request.TypeRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    private final ConfigProperties configProperties;

    public String send(String type) {
        try {
            String res = (String)rabbitTemplate.convertSendAndReceive(configProperties.getExchange(), configProperties.getRoutingKey(), new TypeRequest(type).toJSONString());
            log.info(res);
            return res;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CentralServerException("An error occurred during communication with the central server. Requested type: "+type);
        }
    }

}
