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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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
    public void getPackageSizeTest() {
        String queueResponse = "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":5},{\"id\":2,\"description\":\"Medium\",\"priceFactor\":10},{\"id\":3,\"description\":\"Large\",\"priceFactor\":15}]";
        String queueMessage = "{\"type\":\"packageSize\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> sizes = shippingService.getPackageSize();
        assertEquals(sizes.size(), 3);
    }

    @Test
    public void getPackageSizeElementsTest() {
        String queueResponse = "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":5},{\"id\":2,\"description\":\"Medium\",\"priceFactor\":10},{\"id\":3,\"description\":\"Large\",\"priceFactor\":15}]";
        String queueMessage = "{\"type\":\"packageSize\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> sizes = shippingService.getPackageSize();
        assertThat(sizes, hasItems("Small", "Medium", "Large"));
    }

    @Test(expected = QueueException.class)
    public void getPackageSizeExceptionTest(){
        String queueMessage = "{\"type\":\"packageSize\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(null);
        shippingService.getPackageSize();
    }

    @Test
    public void getPackageTypeTest() {
        String queueResponse = "[{\"id\":2,\"description\":\"Box\",\"price\":10},{\"id\":3,\"description\":\"Envelope\",\"price\":5}]";
        String queueMessage = "{\"type\":\"packageType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> types = shippingService.getPackageType();
        assertEquals(types.size(), 2);
    }

    @Test
    public void getPackageTypeElementsTest() {
        String queueResponse = "[{\"id\":2,\"description\":\"Box\",\"price\":10},{\"id\":3,\"description\":\"Envelope\",\"price\":5}]";
        String queueMessage = "{\"type\":\"packageType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> types = shippingService.getPackageType();
        assertThat(types, hasItems("Box", "Envelope"));
    }

    @Test(expected = QueueException.class)
    public void getPackageTypeExceptionTest(){
        String queueMessage = "{\"type\":\"packageType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(null);
        shippingService.getPackageType();
    }
}