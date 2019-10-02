package com.shipping.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.backend.config.AppConfiguration;
import com.shipping.backend.config.CustomException;
import com.shipping.backend.config.QueueClient;
import com.shipping.backend.entities.*;
import com.shipping.backend.services.QueueResponseHandler;
import com.shipping.backend.services.QueueResponseHandlerImp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BackendApplicationTests {

    private  RabbitTemplate rabbitTemplate;
    private  QueueClient shippingRequestSender;
    private  QueueResponseHandler queueResponseHandler;
    private  AppConfiguration appConfiguration;
    private  QueueRequestMessage queueRequestMessage;
    private  QueueRouteRequestMessage queueRouteRequestMessage;
    private  ObjectMapper mapper;

    @Before
    public void setUp(){

        //Initialize functional classes for testing
        mapper = new ObjectMapper();
        queueRequestMessage = new QueueRequestMessage();
        queueRouteRequestMessage = new QueueRouteRequestMessage();
        appConfiguration = new AppConfiguration();
        rabbitTemplate = mock(RabbitTemplate.class);
        shippingRequestSender = new QueueClient(rabbitTemplate);
        queueResponseHandler = new QueueResponseHandlerImp(shippingRequestSender, appConfiguration, mapper);

    }

	@Test
	public void getPackageTypesTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("packageType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageTypes("packageType");

        //Mocked Response Values
        PackageType packageType = new PackageType();
        packageType.setId(1);
        packageType.setDescription("Box");
        packageType.setPrice(100);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                packageType.toString());
        List packageTypesList = queueResponseHandler.getTypes();

        assertEquals(packageTypesList.size(),1);
        assertEquals(packageTypesList.get(0).getClass(), PackageType.class);
        assertThat(packageTypesList.get(0), hasProperty("description", is("Box")));

	}

    @Test(expected = CustomException.class)
    public void getPackageTypesTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("packageType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageTypes("packageType");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        List packageTypesList = queueResponseHandler.getTypes();

    }

    @Test
    public void getPackageSizesTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("packageSize");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageSizes("packageSize");

        //Mocked Response Values
        PackageSize packageSize = new PackageSize();
        packageSize.setId(1);
        packageSize.setDescription("Small");
        packageSize.setPriceFactor(5);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                packageSize.toString());
        List packageSizesList = queueResponseHandler.getSizes();

        assertEquals(packageSizesList.size(),1);
        assertEquals(packageSizesList.get(0).getClass(), PackageSize.class);
        assertThat(packageSizesList.get(0), hasProperty("description", is("Small")));

    }

    @Test(expected = CustomException.class)
    public void getPackageSizesTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("packageSize");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setPackageSizes("packageSize");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        List packageSizesList = queueResponseHandler.getSizes();

    }

    @Test
    public void getTransportTypesTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("transportType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setTransportTypes("transportType");

        //Mocked Response Values
        Transport transportType = new Transport();
        transportType.setId(2);
        transportType.setDescription("Land");
        transportType.setPricePerMile(2);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                transportType.toString());
        List transportTypesList = queueResponseHandler.getTransports();

        assertEquals(transportTypesList.size(),1);
        assertEquals(transportTypesList.get(0).getClass(), Transport.class);
        assertThat(transportTypesList.get(0), hasProperty("description", is("Land")));

    }

    @Test(expected = CustomException.class)
    public void getTransportTypesTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("transportType");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setTransportTypes("transportType");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        List transportTypesList = queueResponseHandler.getTransports();

    }

    @Test
    public void getTransportVelocityTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("transportVelocity");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setTransportVelocity("transportVelocity");

        //Mocked Response Values
        TransportVelocity transportVelocity = new TransportVelocity();
        transportVelocity.setId(1);
        transportVelocity.setDescription("Regular");
        transportVelocity.setPriceFactor(5);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                transportVelocity.toString());
        List transportVelocityList = queueResponseHandler.getTransportVelocity();

        assertEquals(transportVelocityList.size(),1);
        assertEquals(transportVelocityList.get(0).getClass(), TransportVelocity.class);
        assertThat(transportVelocityList.get(0), hasProperty("description", is("Regular")));

    }

    @Test(expected = CustomException.class)
    public void getTransportVelocityTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("transportVelocity");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setTransportVelocity("transportVelocity");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        List transportTypesList = queueResponseHandler.getTransportVelocity();

    }

    @Test
    public void getCitiesTestSuccess()  {

        //Set request message to get package types
        queueRequestMessage.setType("city");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setCities("city");

        //Mocked Response Values
        City city = new City();
        city.setId(1);
        city.setName("Leon");
        city.setTax(10);
        city.setSeaport(false);
        city.setAirport(false);

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(
                city.toString());
        List cityList = queueResponseHandler.getCities();

        assertEquals(cityList.size(),1);
        assertEquals(cityList.get(0).getClass(), City.class);
        assertThat(cityList.get(0), hasProperty("name", is("Leon")));

    }

    @Test(expected = CustomException.class)
    public void getCitiesTestFailure()  {
        //Set request message to get package types
        queueRequestMessage.setType("city");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setCities("city");

        when(rabbitTemplate.convertSendAndReceive(queueRequestMessage.toString())).thenReturn(null);
        List cityList = queueResponseHandler.getCities();

    }

    @Test
    public void getRoutesTestSuccess()  {

        //Set request message to get package types
        queueRouteRequestMessage.setType("routesList");
        queueRouteRequestMessage.setOrigin("Chihuahua");
        queueRouteRequestMessage.setDestination("Cancun");

        //This line should be remove once i can implement TestPropertySource
        appConfiguration.setRouteList("routesList");

        //Mocked Response Values
        Route route1 = new Route();
        route1.setFrom("Chihuahua");
        route1.setTo("Durango");
        route1.setDistance("10");

        Route route2 = new Route();
        route2.setFrom("Durango");
        route2.setTo("Cancun");
        route2.setDistance("20");

        List<Route> routeList = new ArrayList<>();

        routeList.add(route1);
        routeList.add(route2);

        System.out.println(route1.RoutesToString(routeList));
        System.out.println(queueRouteRequestMessage.toString());

        when(rabbitTemplate.convertSendAndReceive(queueRouteRequestMessage.toString())).thenReturn(
                route1.RoutesToString(routeList));
        queueResponseHandler.getRoutes();

    }

}
