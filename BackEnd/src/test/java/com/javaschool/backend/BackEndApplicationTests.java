package com.javaschool.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.queue.*;
import com.javaschool.entitymapper.PackageType;
import com.javaschool.service.*;
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
@SpringBootTest
public class BackEndApplicationTests {
    private static RabbitTemplate rabbitTemplateMock;
    private static BackEndService backEndService;

    private static MessageType messageType = new MessageType();
    private static ObjectMapper mapper= new ObjectMapper();

    @BeforeClass
    public static void setUp(){
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
}