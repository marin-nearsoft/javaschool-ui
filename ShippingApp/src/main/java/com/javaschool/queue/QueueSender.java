package com.javaschool.queue;

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

    public void sendMessage() {
        String message = (String) rabbitTemplate.convertSendAndReceive(EXCHANGE_SHIPPING, ROUTING_KEY_SHIPPING, "Hello world :3");
        logger.info(message);
    }

    public List<String> getSize(){
        List<String> sizes = new ArrayList<>();
        sizes.add("Small");
        sizes.add("Medium");
        sizes.add("Large");
        sendMessage();
        return sizes;
    }

    public List<String> getType(){
        List<String> types = new ArrayList<>();
        types.add("Envelope");
        types.add("Box");
        return types;
    }

    public List<String> getTime(){
        List<String> times = new ArrayList<>();
        times.add("Express");
        times.add("Regular");
        times.add("Slow");
        return times;
    }

    public List<String> getTransport(){
        List<String> transports = new ArrayList<>();
        transports.add("Land");
        transports.add("Air");
        return transports;
    }

}
