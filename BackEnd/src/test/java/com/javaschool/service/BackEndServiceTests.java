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
        QueueResponseService queueResponseService = new QueueResponseServiceImp(queueSenderService,mapper);
        backEndService = new BackEndServiceImp(queueResponseService,responseList);
    }

    @Test
    public void getTypeTest() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Box");
        expected.add("Envelope");
        messageType.setType("packageType");
        messageType.setOrigin(null);
        messageType.setDestination(null);

        PackageType box = new PackageType();
        box.setId(1);
        box.setDescription("Box");
        box.setPrice(10);

        PackageType envelope = new PackageType();
        envelope.setId(1);
        envelope.setDescription("Envelope");
        envelope.setPrice(10);


        List<PackageType> packageTypeResponse = new java.util.ArrayList<>(Collections.emptyList());
        packageTypeResponse.add(box);
        packageTypeResponse.add(envelope);

        String mockType = new ObjectMapper().writeValueAsString(packageTypeResponse);

        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockType);



        List<String> actual = backEndService.getType();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSizeTest() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Big");
        expected.add("Small");
        messageType.setType("packageSize");
        messageType.setOrigin(null);
        messageType.setDestination(null);

        PackageSize small = new PackageSize();
        small.setId(1);
        small.setDescription("Small");
        small.setPriceFactor(10);

        PackageSize big = new PackageSize();
        big.setId(1);
        big.setDescription("Big");
        big.setPriceFactor(12);

        List<PackageSize> packageSizeResponse = new java.util.ArrayList<>(Collections.emptyList());
        packageSizeResponse.add(small);
        packageSizeResponse.add(big);

        String mockSize = new ObjectMapper().writeValueAsString(packageSizeResponse);

        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockSize);

        List<String> actual = backEndService.getSize();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getTransportTest() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Air");
        expected.add("Land");
        messageType.setType("transportType");
        messageType.setOrigin(null);
        messageType.setDestination(null);

        TransportType land = new TransportType();
        land.setId(1);
        land.setDescription("Land");
        land.setPricePerMile(2);

        TransportType air = new TransportType();
        air.setId(2);
        air.setDescription("Air");
        air.setPricePerMile(13);


        List<TransportType> transportTypeResponse = new java.util.ArrayList<>(Collections.emptyList());
        transportTypeResponse.add(land);
        transportTypeResponse.add(air);

        String mockTransport = new ObjectMapper().writeValueAsString(transportTypeResponse);


        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockTransport);


        List<String> actual = backEndService.getTransport();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getVelocityTest() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Express");
        expected.add("Regular");
        messageType.setType("transportVelocity");
        messageType.setOrigin(null);
        messageType.setDestination(null);

        TransportVelocity regular = new TransportVelocity();
        regular.setId(1);
        regular.setDescription("Regular");
        regular.setPriceFactor(5);

        TransportVelocity express = new TransportVelocity();
        express.setId(2);
        express.setDescription("Express");
        express.setPriceFactor(10);

        List<TransportVelocity> transportVelocityResponse = new java.util.ArrayList<>(Collections.emptyList());
        transportVelocityResponse.add(regular);
        transportVelocityResponse.add(express);

        String mockVelocity = new ObjectMapper().writeValueAsString(transportVelocityResponse);


        String mockRequest = new ObjectMapper().writeValueAsString(messageType);
        when(rabbitTemplateMock.convertSendAndReceive(mockRequest)).thenReturn(mockVelocity);

        List<String> actual = backEndService.getVelocity();
        Assert.assertEquals(expected, actual);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getCityTest() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
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
        String expected = "Chihuahua, Tampico, Puebla, Acapulco ";

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

        String actual = backEndService.getRoute("Chihuahua","Acapulco");

        Assert.assertEquals(expected, actual);

    }

}