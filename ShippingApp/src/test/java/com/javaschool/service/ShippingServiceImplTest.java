package com.javaschool.service;

import com.javaschool.common.GlobalProperties;
import com.javaschool.common.QueueException;
import com.javaschool.queue.QueueSender;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShippingServiceImplTest {

    private QueueSender queueSender;
    private GlobalProperties globalProperties;
    private AmqpTemplate amqpTemplateMock;
    private ShippingService shippingService;

    @Before
    public void setup() {
        amqpTemplateMock = mock(RabbitTemplate.class);
        globalProperties = new GlobalProperties();
        queueSender = new QueueSender(amqpTemplateMock, globalProperties);
        shippingService = new ShippingServiceImpl(queueSender);
    }

    @Test
    public void getSizeTest() {
        String queueResponse = "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":5},{\"id\":2,\"description\":\"Medium\",\"priceFactor\":10},{\"id\":3,\"description\":\"Large\",\"priceFactor\":15}]";
        String queueMessage = "{\"type\":\"packageSize\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> sizes = shippingService.getSize();
        assertEquals(sizes.size(), 3);
    }

    @Test
    public void getSizeElementsTest() {
        String queueResponse = "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":5},{\"id\":2,\"description\":\"Medium\",\"priceFactor\":10},{\"id\":3,\"description\":\"Large\",\"priceFactor\":15}]";
        String queueMessage = "{\"type\":\"packageSize\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> sizes = shippingService.getSize();
        assertThat(sizes, hasItems("Small", "Medium", "Large"));
    }

    @Test(expected = QueueException.class)
    public void getSizeExceptionTest(){
        String queueMessage = "{\"type\":\"packageSize\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(null);
        List<String> sizes = shippingService.getSize();
    }

    @Test
    public void getTypeTest() {
        String queueResponse = "[{\"id\":2,\"description\":\"Box\",\"price\":10},{\"id\":3,\"description\":\"Envelope\",\"price\":5}]";
        String queueMessage = "{\"type\":\"packageType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> types = shippingService.getType();
        assertEquals(types.size(), 2);
    }

    @Test
    public void getTypeElementsTest() {
        String queueResponse = "[{\"id\":2,\"description\":\"Box\",\"price\":10},{\"id\":3,\"description\":\"Envelope\",\"price\":5}]";
        String queueMessage = "{\"type\":\"packageType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> types = shippingService.getType();
        assertThat(types, hasItems("Box", "Envelope"));
    }

    @Test(expected = QueueException.class)
    public void getTypeExceptionTest(){
        String queueMessage = "{\"type\":\"packageType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(null);
        List<String> types = shippingService.getSize();
    }
}