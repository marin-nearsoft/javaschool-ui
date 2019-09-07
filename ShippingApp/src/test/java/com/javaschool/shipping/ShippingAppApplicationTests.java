package com.javaschool.shipping;

import com.javaschool.model.ShippingService;
import com.javaschool.model.ShippingServiceImpl;
import com.javaschool.queue.QueueSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShippingAppApplicationTests {

    private RabbitTemplate rabbitTemplate = new RabbitTemplate();

    private QueueSender queueSender = new QueueSender(rabbitTemplate);

    private ShippingService shippingService = new ShippingServiceImpl(queueSender);

    @Test
    public void getSizeTest() {
        List<String> sizes = shippingService.getSize();
        assertEquals(sizes.size(), 3);
    }

    @Test
    public void getTypeTest() {
        List<String> types = shippingService.getType();
        assertEquals(types.size(), 2);
    }

    @Test
    public void getTimeTest() {
        List<String> times = shippingService.getTime();
        assertEquals(times.size(), 3);
    }

    @Test
    public void getTransportTest() {
        List<String> transports = shippingService.getTransport();
        assertEquals(transports.size(), 2);
    }

}