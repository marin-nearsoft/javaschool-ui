package com.nearsoft.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nearsoft.project.rabbitmq.implementation.RabbitmqImpl;
import com.nearsoft.project.resources.implementation.ShippingListsImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ShippingListsImplTest {

    @Value("test_exchange")
    private String exchange;

    @Value("test_routing-key")
    private String routingKey;

    private RabbitTemplate rabbitMock;
    private ShippingListsImpl shippingListsMock;
    private ShippingListsImpl shippingLists;
    private ObjectMapper mapperMock;
    private RabbitmqImpl rabbitTemplateMock;

    @Before
    public void config(){
        rabbitMock = Mockito.mock(RabbitTemplate.class);
        shippingListsMock = Mockito.mock(ShippingListsImpl.class);
        mapperMock = new ObjectMapper();
        shippingLists = new ShippingListsImpl(mapperMock);
        rabbitTemplateMock = new RabbitmqImpl(rabbitMock);
    }

    @Test
    public void testMassageNull() throws IOException {
        Mockito.when(rabbitMock.convertSendAndReceive(Mockito.eq(exchange),
                Mockito.eq(routingKey),Mockito.any(Object.class))).thenReturn(null);

        String ressult = rabbitTemplateMock.getMessage("type");
        assertTrue(ressult == null);
    }

    @Test
    public void testTypes() throws IOException {
        String json = "[{\"id\": 2,\"description\": \"Box\",\"price\": 10}]";
        List<String> typesList = shippingLists.getTypes(json);
        assertTrue(!typesList.isEmpty());
    }
    @Test
    public void testSizes() throws IOException {
        String json = "[{\"id\": 2,\"description\": \"Small\",\"priceFactor\": 10}]";
        List<String> sizesList = shippingLists.getSizes(json);
        assertTrue(!sizesList.isEmpty());
    }
}
