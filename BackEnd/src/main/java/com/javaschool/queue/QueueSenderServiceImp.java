package com.javaschool.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueSenderServiceImp implements QueueSenderService {

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper mapper;

    public QueueSenderServiceImp(final RabbitTemplate rabbitTemplate, final ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }


    public String sendRequest(String requestType) throws JsonProcessingException {
        MessageType messageType = new MessageType();
        messageType.setType(requestType);

        String message = mapper.writeValueAsString(messageType);

        return (String) rabbitTemplate.convertSendAndReceive(message);
    }
}
