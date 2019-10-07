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
import java.util.ArrayList;
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
    private static ResponseList responseList = new ResponseList();

    @BeforeClass
    public static void setUp() {
        rabbitTemplateMock = mock(RabbitTemplate.class);
        QueueSenderService queueSenderService = new QueueSenderServiceImp(rabbitTemplateMock, mapper);
        QueueResponseService queueResponseService = new QueueResponseServiceImp(queueSenderService, mapper);
        backEndService = new BackEndServiceImp(queueResponseService,responseList);
    }

    @Test
    public void getTypeTest() throws IOException {
        List<String> expected = Collections.singletonList("Box");
        messageType.setType("packageType");
        messageType.setOrigin(null);
        messageType.setDestination(null);

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
        messageType.setOrigin(null);
        messageType.setDestination(null);

        PackageSize packageSizeResponse = new PackageSize();
        packageSizeResponse.setId(1);
        packageSizeResponse.setDescription("Small");
        packageSizeResponse.setPriceFactor(10);

        PackageSize[] sizeResponseArray = new PackageSize[]{packageSizeResponse};

        String mockSize = new ObjectMapper().writeValueAsString(sizeResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockSize);

        List<String> actual = backEndService.getSize();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getTransportTest() throws IOException {
        List<String> expected = Collections.singletonList("Land");
        messageType.setType("transportType");
        messageType.setOrigin(null);
        messageType.setDestination(null);

        TransportType transportTypeResponse = new TransportType();
        transportTypeResponse.setId(1);
        transportTypeResponse.setDescription("Land");
        transportTypeResponse.setPricePerMile(2);

        TransportType[] transportResponseArray = new TransportType[]{transportTypeResponse};

        String mockTransport = new ObjectMapper().writeValueAsString(transportResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockTransport);

        List<String> actual = backEndService.getTransport();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getVelocityTest() throws IOException {
        List<String> expected = Collections.singletonList("Regular");
        messageType.setType("transportVelocity");
        messageType.setOrigin(null);
        messageType.setDestination(null);

        TransportVelocity transportVelocityResponse = new TransportVelocity();
        transportVelocityResponse.setId(1);
        transportVelocityResponse.setDescription("Regular");
        transportVelocityResponse.setPriceFactor(5);

        TransportVelocity[] velocityResponseArray = new TransportVelocity[]{transportVelocityResponse};

        String mockVelority = new ObjectMapper().writeValueAsString(velocityResponseArray);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockVelority);

        List<String> actual = backEndService.getVelocity();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getCityTest() throws IOException {
        ArrayList expected = new ArrayList();
        expected.add("Aguascalientes");
        expected.add("Chihuahua");
        expected.add("Leon");

        messageType.setType("city");
        messageType.setOrigin(null);
        messageType.setDestination(null);

        Cities Aguascalientes = new Cities();
        Aguascalientes.setId(1);
        Aguascalientes.setName("Aguascalientes");
        Aguascalientes.setTax(10);
        Aguascalientes.setAirport(true);
        Aguascalientes.setSeaport(false);

        Cities Chihuahua = new Cities();
        Chihuahua.setId(2);
        Chihuahua.setName("Chihuahua");
        Chihuahua.setTax(5);
        Chihuahua.setAirport(true);
        Chihuahua.setSeaport(false);

        Cities Leon = new Cities();
        Leon.setId(3);
        Leon.setName("Leon");
        Leon.setTax(2);
        Leon.setAirport(true);
        Leon.setSeaport(false);

        List<Cities> citiesResponseArray = new java.util.ArrayList<>(Collections.emptyList());
        citiesResponseArray.add(Aguascalientes);
        citiesResponseArray.add(Chihuahua);
        citiesResponseArray.add(Leon);

        String mockCities = new ObjectMapper().writeValueAsString(citiesResponseArray);

        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockCities);

        List<String> actual = backEndService.getCity();

        Assert.assertEquals(expected, actual);


    }

    @Test
    public void getRouteTest() throws IOException {
        ArrayList expected = new ArrayList();
        expected.add("Chihuahua");
        expected.add("Tampico");
        expected.add("Puebla");
        expected.add("Acapulco");

        messageType.setType("routesList");
        messageType.setOrigin("Chihuahua");
        messageType.setDestination("Acapulco");

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


        String mockRoutes = new ObjectMapper().writeValueAsString(routeLists);
        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockRoutes);

        List<String> actual = backEndService.getRoute("Chihuahua","Acapulco");

        Assert.assertEquals(expected, actual);

    }

}