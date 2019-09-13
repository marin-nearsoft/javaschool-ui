package com.JavaSchool.Queue;


import com.JavaSchool.EntityMapper.PackageType;
import com.JavaSchool.EntityMapper.MessageType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QueueClient {
    private static final Logger logger = LoggerFactory.getLogger(QueueClient.class);

    private RabbitTemplate rabbitTemplate;

    @Value("${rabbit.routing-key")
    private String ROUTING_KEY;

    @Value("${rabbit.exchange}")
    private String EXCHANGE;

    public QueueClient(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private String Request(String requestType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MessageType messageType = new MessageType();
        messageType.setType(requestType);

        String message = mapper.writeValueAsString(messageType);

        return (String) rabbitTemplate.convertSendAndReceive(EXCHANGE, ROUTING_KEY, message);
    }

    public List<String> getType() {
        ObjectMapper mapper = new ObjectMapper();
        List<PackageType> types;
        List<String> listoftypes = new ArrayList<>();
        try{
            String response = Request("packageType");
            types = mapper.readValue(response, new TypeReference<List<PackageType>>(){});
            for (PackageType type : types) {
                listoftypes.add(type.getDescription());
            }
        } catch(JsonParseException e){
            logger.error(e.getMessage());
        } catch(IOException e){
            logger.error(e.getMessage());
        }
        return listoftypes;
    }

}
