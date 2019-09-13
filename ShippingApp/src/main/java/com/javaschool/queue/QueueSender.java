package com.javaschool.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.common.PackageSize;
import com.javaschool.common.PackageType;
import com.javaschool.common.QueueException;
import com.javaschool.common.QueueMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueueSender {

    private static final Logger logger = LoggerFactory.getLogger(QueueSender.class);

    private AmqpTemplate rabbitTemplate;

    @Value("${config-rabbit.routing-key}")
    public String ROUTING_KEY_SHIPPING;

    @Value("${config-rabbit.exchange}")
    public String EXCHANGE_SHIPPING;

    public QueueSender(final AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private String sendMessage(String queueMessage) {
        String message = (String) rabbitTemplate.convertSendAndReceive(EXCHANGE_SHIPPING, ROUTING_KEY_SHIPPING, queueMessage);
        logger.info(message);
        return message;
    }

    private String messageRequest(String type){
        ObjectMapper objectMapper = new ObjectMapper();
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
        ObjectMapper objectMapper = new ObjectMapper();
        List<PackageSize> sizes;
        List<String> sizesName = new ArrayList<>();
        try{
            String message = messageRequest("packageSize");
            sizes = objectMapper.readValue(message, new TypeReference<List<PackageSize>>(){});
            for (PackageSize size : sizes) {
                sizesName.add(size.getDescription());
            }
        } catch(Exception e){
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return sizesName;
    }

    public List<String> getType() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<PackageType> types;
        List<String> typesName = new ArrayList<>();
        try{
            String message = messageRequest("packageType");
            types = objectMapper.readValue(message, new TypeReference<List<PackageType>>(){});
            for (PackageType type : types) {
                typesName.add(type.getDescription());
            }
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
