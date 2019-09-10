package com.javaschool.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.common.QueueMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueueSender {

    private static final Logger logger = LoggerFactory.getLogger(QueueSender.class);

    private RabbitTemplate rabbitTemplate;

    @Value("${config-rabbit.routing-key}")
    public String ROUTING_KEY_SHIPPING;

    @Value("${config-rabbit.exchange}")
    public String EXCHANGE_SHIPPING;

    public QueueSender(final RabbitTemplate rabbitTemplate) {
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
        List<String> sizes = new ArrayList<>();
        sizes.add("Small");
        sizes.add("Medium");
        sizes.add("Large");

        String message = messageRequest("packageSize");
        return sizes;
    }

    public List<String> getType() {
        List<String> types = new ArrayList<>();
        types.add("Envelope");
        types.add("Box");
        return types;
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
