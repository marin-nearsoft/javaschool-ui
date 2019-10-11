package com.shipping.backend.config;

import com.shipping.backend.models.common.MockResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueClient {

    private final static Logger log = LoggerFactory.getLogger(QueueClient.class);

    private RabbitTemplate rabbitTemplate;
    private MockResponses mockResponses = new MockResponses();

    public QueueClient(final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public String sendRequest(String message) {
        log.info("Message send to the queue {}", message);
        String response = (String) rabbitTemplate.convertSendAndReceive(message);
        if(response==null){
            response = mockResponses.messageResponse(message);
        }
        log.info("Message received from the queue {}", response);
        return response;
    }
}
