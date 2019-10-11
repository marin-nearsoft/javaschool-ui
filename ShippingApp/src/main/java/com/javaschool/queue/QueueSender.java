package com.javaschool.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.common.GlobalProperties;
import com.javaschool.common.Mocks;
import com.javaschool.common.QueueException;
import com.javaschool.common.QueueMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class QueueSender {

    private static final Logger logger = LoggerFactory.getLogger(QueueSender.class);
    private AmqpTemplate rabbitTemplate;
    private GlobalProperties globalProperties;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Mocks mocks = new Mocks();

    public QueueSender(final AmqpTemplate rabbitTemplate, GlobalProperties globalProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.globalProperties = globalProperties;
    }

    private String sendMessage(QueueMessageRequest messageRequest) throws Exception {
        String queueMessage = objectMapper.writeValueAsString(messageRequest);
        String message = (String) rabbitTemplate.convertSendAndReceive(globalProperties.getExchangeShipping(), globalProperties.getRoutingKeyShipping(), queueMessage);
        logger.info(message);
        return message;
    }

    public <T> List<T> messageRequest(QueueMessageRequest messageRequest, T[] typeClass) {
        List messageResponse;
        try {
            String queueResponse = sendMessage(messageRequest);
            if(queueResponse == null){
                queueResponse = mocks.messageResponse(messageRequest);
            }
            messageResponse = Arrays.asList(objectMapper.readValue(queueResponse, typeClass.getClass()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return messageResponse;
    }
}
