package com.javaschool.queue;

import com.javaschool.model.ShippingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueListener {

    @Autowired
    private ShippingService shippingService;

    @RabbitListener(queues = "${config-rabbit.queue}")
    public String listener(String in) {
        return "Message read from queue: " + in;
    }

}
