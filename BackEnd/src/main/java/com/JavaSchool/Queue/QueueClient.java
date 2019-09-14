package com.JavaSchool.Queue;


import com.JavaSchool.EntityMapper.MessageType;
import com.JavaSchool.EntityMapper.PackageType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class QueueClient {
    private static final Logger logger = LoggerFactory.getLogger(QueueClient.class);

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper mapper;

    public QueueClient(final RabbitTemplate rabbitTemplate, final ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    private String Request(String requestType) throws JsonProcessingException {
        MessageType messageType = new MessageType();
        messageType.setType(requestType);

        String message = mapper.writeValueAsString(messageType);

        return (String) rabbitTemplate.convertSendAndReceive( message);
    }

    public List<PackageType> getType() {
        List<PackageType> types = null;
        try{
            String response = Request("packageType");
            types = mapper.readValue(response, new TypeReference<List<PackageType>>(){});
        } catch(IOException e){
            logger.error(e.getMessage());
        }
        return types;
    }

}
