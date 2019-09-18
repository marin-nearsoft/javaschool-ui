package com.javaschool.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueueSender {

    private static final Logger logger = LoggerFactory.getLogger(QueueSender.class);

    private AmqpTemplate rabbitTemplate;

    private GlobalProperties globalProperties;

    public QueueSender(final AmqpTemplate rabbitTemplate, GlobalProperties globalProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.globalProperties = globalProperties;
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    private String sendMessage(String queueMessage) {
        String message = (String) rabbitTemplate.convertSendAndReceive(globalProperties.getExchangeShipping(), globalProperties.getRoutingKeyShipping(), queueMessage);
        logger.info(message);
        return message;
    }

    private String messageRequest(String type){
        ObjectMapper objectMapper = objectMapper();
        QueueMessageRequest messageRequest = new QueueMessageRequest();
        String message;
        String queueResponse = "";
        messageRequest.setType(type);
        try {
            message = objectMapper.writeValueAsString(messageRequest);
            queueResponse = sendMessage(message);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return queueResponse;
    }

    public List<String> getSize() {
        ObjectMapper objectMapper = objectMapper();
        List<PackageSize> sizes;
        List<String> sizesName;
        try{
            String message = messageRequest("packageSize");
            sizes = objectMapper.readValue(message, new TypeReference<List<PackageSize>>(){});
            sizesName = sizes.stream()
                    .map(size->size.getDescription())
                    .collect(Collectors.toList());
        } catch(Exception e){
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return sizesName;
    }

    public List<String> getType() {
        ObjectMapper objectMapper = objectMapper();
        List<PackageType> types;
        List<String> typesName;
        try{
            String message = messageRequest("packageType");
            types = objectMapper.readValue(message, new TypeReference<List<PackageType>>(){});
            typesName = types.stream()
                    .map(type->type.getDescription())
                    .collect(Collectors.toList());
        } catch(Exception e){
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return typesName;
    }

    public List<String> getTime() {
        List<String> times = new ArrayList<>();
        times.add("Express");
        times.add("Regular");
        times.add("Slow");
        return times;
    }

    public List<String> getTransport() {
        List<String> transports = new ArrayList<>();
        transports.add("Land");
        transports.add("Air");
        return transports;
    }
}
