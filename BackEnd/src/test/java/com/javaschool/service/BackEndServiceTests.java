package com.javaschool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.dijkstra.RouteList;
import com.javaschool.modelmapper.*;
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
    private static MessageWithOrigin messageWithOrigin = new MessageWithOrigin();

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

    }

    @Test
    public void getVelocityTest() throws IOException {
        List<String> expected = Collections.singletonList("Regular");
        messageType.setType("transportVelocity");

        TransportVelocity transportVelocityResponse = new TransportVelocity();
        transportVelocityResponse.setId(1);
        transportVelocityResponse.setDescription("Regular");
        transportVelocityResponse.setPriceFactor(5);

        TransportVelocity[] velocityResponseArray = new TransportVelocity[]{transportVelocityResponse};

        String mockTypes = new ObjectMapper().writeValueAsString(velocityResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockTypes);

        List<String> actual = backEndService.getVelocity();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getCityTest() throws IOException {
        List<String> expected = Collections.singletonList("Leon");
        messageType.setType("city");

        Cities citiesResponse = new Cities();
        citiesResponse.setId(1);
        citiesResponse.setName("Leon");
        citiesResponse.setAirport(true);
        citiesResponse.setSeaport(false);

        Cities[] citiesResponseArray = new Cities[]{citiesResponse};

        String mockTypes = new ObjectMapper().writeValueAsString(citiesResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockTypes);

        List<String> actual = backEndService.getCity();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getRouteTest() throws IOException {
        List<String> expected = Collections.singletonList("\"Chihuahua\",\"Tampico\",\"Puebla\",\"Acapulco\"");
        messageWithOrigin.setType("routesList");
        messageWithOrigin.setOrigin("Chihuahua");
        messageWithOrigin.setDestination("Acapulco");

        RouteList chihuahua = new RouteList();
        chihuahua.setFrom("Chihuahua");
        chihuahua.setTo("Tampico");
        chihuahua.setDistance(5);

        RouteList Tampico = new RouteList();
        Tampico.setFrom("Tampico");
        Tampico.setTo("Puebla");
        Tampico.setDistance(2);

        RouteList Puebla = new RouteList();
        Puebla.setFrom("Puebla");
        Puebla.setTo("Acapulco");
        Puebla.setDistance(9);

        List<RouteList> routeLists = new java.util.ArrayList<>(Collections.emptyList());

        routeLists.add(chihuahua);
        routeLists.add(Tampico);
        routeLists.add(Puebla);


/*

        RouteList[] routeResponseArray = new RouteList[]{(RouteList) routeLists};

        String mockTypes = new ObjectMapper().writeValueAsString(routeResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageWithOrigin);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockTypes);

        List<String> actual = backEndService.getRoute("Chihuahua","Acapulco");

        Assert.assertEquals(expected, actual);
*/
    }
}