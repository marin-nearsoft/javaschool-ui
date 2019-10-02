package com.javaschool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.entitymapper.PackageSize;
import com.javaschool.entitymapper.PackageType;
import com.javaschool.entitymapper.TransportType;
import com.javaschool.queue.*;
import com.sun.glass.ui.Application;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BackEndServiceTests {
    private static RabbitTemplate rabbitTemplateMock;
    private static BackEndService backEndService;

    private static MessageType messageType = new MessageType();
    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeClass
    public static void setUp() {
        rabbitTemplateMock = mock(RabbitTemplate.class);
        QueueSenderService queueSenderService = new QueueSenderServiceImp(rabbitTemplateMock, mapper);
        QueueResponseService queueResponseService = new QueueResponseServiceImp(queueSenderService, mapper);
        backEndService = new BackEndServiceImp(queueResponseService);
    }

    @Test
    public void getTypeTest() throws IOException {
        List<String> expected = Collections.singletonList("Box");
        messageType.setType("packageType");

        PackageType packageTypeResponse = new PackageType();
        packageTypeResponse.setId(1);
        packageTypeResponse.setDescription("Box");
        packageTypeResponse.setPrice(10);

        PackageType[] typeResponseArray = new PackageType[]{packageTypeResponse};

        String mockTypes = new ObjectMapper().writeValueAsString(typeResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockTypes);

        List<String> actual = backEndService.getType();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSizeTest() throws IOException {
        List<String> expected = Collections.singletonList("Small");
        messageType.setType("packageSize");

        PackageSize packageSizeResponse = new PackageSize();
        packageSizeResponse.setId(1);
        packageSizeResponse.setDescription("Small");
        packageSizeResponse.setPriceFactor(10);

        PackageSize[] sizeResponseArray = new PackageSize[]{packageSizeResponse};

        String mockTypes = new ObjectMapper().writeValueAsString(sizeResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockTypes);

        List<String> actual = backEndService.getSize();

        Assert.assertEquals(expected, actual);
        System.out.println("Expected:" + expected + ":" + actual);
    }

    @Test
    public void getTransportTest() throws IOException {
        List<String> expected = Collections.singletonList("Land");
        messageType.setType("transportType");

        TransportType transportTypeResponse = new TransportType();
        transportTypeResponse.setId(1);
        transportTypeResponse.setDescription("Land");
        transportTypeResponse.setPricePerMile(2);

        TransportType[] transportResponseArray = new TransportType[]{transportTypeResponse};

        String mockTypes = new ObjectMapper().writeValueAsString(transportResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockTypes);

        List<String> actual = backEndService.getTransport();

        Assert.assertEquals(expected, actual);
        System.out.println("Expected:" + expected + ":" + actual);
    }
}