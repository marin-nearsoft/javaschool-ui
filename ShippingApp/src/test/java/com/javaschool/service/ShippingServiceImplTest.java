package com.javaschool.service;

import com.javaschool.common.GlobalProperties;
import com.javaschool.common.QueueException;
import com.javaschool.common.ShippingPayload;
import com.javaschool.queue.QueueSender;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShippingServiceImplTest {

    private QueueSender queueSender;
    private GlobalProperties globalProperties;
    private AmqpTemplate amqpTemplateMock;
    private ShippingService shippingService;
    private ShippingPayload shippingPayload;
    private static final String PACKAGE_SIZE_REQUEST = "{\"type\":\"packageSize\",\"origin\":null,\"destination\":null}";
    private static final String PACKAGE_TYPE_REQUEST = "{\"type\":\"packageType\",\"origin\":null,\"destination\":null}";
    private static final String TRANSPORT_VELOCITY_REQUEST = "{\"type\":\"transportVelocity\",\"origin\":null,\"destination\":null}";
    private static final String TRANSPORT_TYPE_REQUEST = "{\"type\":\"transportType\",\"origin\":null,\"destination\":null}";
    private static final String CITY_REQUEST =  "{\"type\":\"city\",\"origin\":null,\"destination\":null}";
    private static final String ROUTE_REQUEST =  "{\"type\":\"routesList\",\"origin\":\"Chihuahua\",\"destination\":\"Cancun\"}";
    private static final String PACKAGE_SIZE_RESPONSE =  "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":5},{\"id\":2,\"description\":\"Medium\",\"priceFactor\":10},{\"id\":3,\"description\":\"Large\",\"priceFactor\":15}]";
    private static final String PACKAGE_TYPE_RESPONSE =  "[{\"id\":2,\"description\":\"Box\",\"price\":10},{\"id\":3,\"description\":\"Envelope\",\"price\":5}]";
    private static final String TRANSPORT_VELOCITY_RESPONSE = "[{\"id\":1,\"description\":\"Regular\",\"priceFactor\":5},{\"id\":2,\"description\":\"Express\",\"priceFactor\":10},{\"id\":3,\"description\":\"Slow\",\"priceFactor\":0}]";
    private static final String TRANSPORT_TYPE_RESPONSE =  "[{\"id\":2,\"description\":\"Land\",\"pricePerMile\":2},{\"id\":1,\"description\":\"Air\",\"pricePerMile\":5}]";
    private static final String CITY_RESPONSE = "[{\"id\":9,\"name\":\"Leon\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":12," +
            "\"name\":\"Cuernavaca\",\"tax\":0,\"seaport\":false,\"airport\":false},{\"id\":23,\"name\":\"Tuxtla Gutierrez\"" +
            ",\"tax\":5,\"seaport\":false,\"airport\":false},{\"id\":26,\"name\":\"Veracruz\",\"tax\":10,\"seaport\":true,\"" +
            "airport\":false},{\"id\":27,\"name\":\"Villahermosa\",\"tax\":0,\"seaport\":false,\"airport\":true}," +
            "{\"id\":28,\"name\":\"Saltillo\",\"tax\":10,\"seaport\":false,\"airport\":true},{\"id\":29,\"name\":\"Tampico\"," +
            "\"tax\":16,\"seaport\":true,\"airport\":true},{\"id\":22,\"name\":\"Cancun\",\"tax\":16,\"seaport\":true," +
            "\"airport\":true},{\"id\":24,\"name\":\"Ciudad del Carmen\",\"tax\":5,\"seaport\":true,\"airport\":false}," +
            "{\"id\":25,\"name\":\"Merida\",\"tax\":16,\"seaport\":true,\"airport\":true},{\"id\":30,\"name\":\"Durango\"" +
            ",\"tax\":10,\"seaport\":false,\"airport\":true},{\"id\":31,\"name\":\"Chihuahua\",\"tax\":10,\"seaport\":false," +
            "\"airport\":true},{\"id\":3,\"name\":\"Cuautitlan\",\"tax\":0,\"seaport\":false,\"airport\":true},{\"id\":2," +
            "\"name\":\"Ensenada\",\"tax\":16,\"seaport\":true,\"airport\":true},{\"id\":5,\"name\":\"San Luis Potosi\"," +
            "\"tax\":10,\"seaport\":false,\"airport\":true},{\"id\":4,\"name\":\"Cdmx\",\"tax\":0,\"seaport\":false," +
            "\"airport\":true},{\"id\":7,\"name\":\"Zacatecas\",\"tax\":5,\"seaport\":false,\"airport\":true},{\"id\":6," +
            "\"name\":\"Pachuca\",\"tax\":5,\"seaport\":false,\"airport\":true},{\"id\":8,\"name\":\"Aguascalientes\"," +
            "\"tax\":5,\"seaport\":false,\"airport\":true},{\"id\":11,\"name\":\"Puebla\",\"tax\":5,\"seaport\":false," +
            "\"airport\":true},{\"id\":10,\"name\":\"Queretaro\",\"tax\":5,\"seaport\":false,\"airport\":true},{\"id\":13,\"" +
            "name\":\"La Paz\",\"tax\":10,\"seaport\":true,\"airport\":true},{\"id\":15,\"name\":\"Guaymas\",\"tax\":16,\"" +
            "seaport\":true,\"airport\":false},{\"id\":14,\"name\":\"Mazatlan\",\"tax\":10,\"seaport\":true,\"airport" +
            "\":true},{\"id\":17,\"name\":\"Tepic\",\"tax\":0,\"seaport\":false,\"airport\":true},{\"id\":16,\"name\":" +
            "\"Puerto Vallarta\",\"tax\":10,\"seaport\":true,\"airport\":true},{\"id\":19,\"name\":\"Manzanillo\",\"tax\"" +
            ":5,\"seaport\":true,\"airport\":true},{\"id\":18,\"name\":\"Lazaro Cardenas\",\"tax\":5,\"seaport\":true,\"" +
            "airport\":false},{\"id\":21,\"name\":\"Acapulco\",\"tax\":10,\"seaport\":true,\"airport\":true},{\"id\":20," +
            "\"name\":\"Puerto Escondido\",\"tax\":0,\"seaport\":true,\"airport\":false},{\"id\":33,\"name\":\"Tlaxcala\"," +
            "\"tax\":0,\"seaport\":false,\"airport\":true},{\"id\":32,\"name\":\"Monterrey\",\"tax\":16,\"seaport\":false," +
            "\"airport\":true}]";
    private static final String ROUTE_RESPONSE = "[\n" +
            "{\n" +
            "\"from\": \"Ensenada\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 91\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Saltillo\",\n" +
            "\"to\": \"Zacatecas\",\n" +
            "\"distance\": 32\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Tlaxcala\",\n" +
            "\"to\": \"Tuxtla Gutierrez\",\n" +
            "\"distance\": 33\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"San Luis Potosi\",\n" +
            "\"to\": \"Manzanillo\",\n" +
            "\"distance\": 6\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Queretaro\",\n" +
            "\"to\": \"Cuernavaca\",\n" +
            "\"distance\": 12\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Mazatlan\",\n" +
            "\"to\": \"Tuxtla Gutierrez\",\n" +
            "\"distance\": 90\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Saltillo\",\n" +
            "\"to\": \"Merida\",\n" +
            "\"distance\": 22\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Chihuahua\",\n" +
            "\"to\": \"La Paz\",\n" +
            "\"distance\": 20\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Tepic\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 69\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Saltillo\",\n" +
            "\"to\": \"Veracruz\",\n" +
            "\"distance\": 4\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Veracruz\",\n" +
            "\"to\": \"Queretaro\",\n" +
            "\"distance\": 79\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Manzanillo\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 25\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Mazatlan\",\n" +
            "\"to\": \"Queretaro\",\n" +
            "\"distance\": 86\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Chihuahua\",\n" +
            "\"to\": \"Puerto Vallarta\",\n" +
            "\"distance\": 80\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Pachuca\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 3\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Chihuahua\",\n" +
            "\"to\": \"Mazatlan\",\n" +
            "\"distance\": 88\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Tampico\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 60\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Merida\",\n" +
            "\"to\": \"Acapulco\",\n" +
            "\"distance\": 80\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Zacatecas\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 16\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Tuxtla Gutierrez\",\n" +
            "\"to\": \"Pachuca\",\n" +
            "\"distance\": 4\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Puebla\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 9\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Pachuca\",\n" +
            "\"to\": \"Lazaro Cardenas\",\n" +
            "\"distance\": 86\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Chihuahua\",\n" +
            "\"to\": \"Pachuca\",\n" +
            "\"distance\": 89\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Puerto Vallarta\",\n" +
            "\"to\": \"Queretaro\",\n" +
            "\"distance\": 25\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Leon\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 79\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Puerto Vallarta\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 13\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Puerto Vallarta\",\n" +
            "\"to\": \"Ensenada\",\n" +
            "\"distance\": 4\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Veracruz\",\n" +
            "\"to\": \"Zacatecas\",\n" +
            "\"distance\": 92\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Tuxtla Gutierrez\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 31\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Queretaro\",\n" +
            "\"to\": \"Leon\",\n" +
            "\"distance\": 82\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Tlaxcala\",\n" +
            "\"to\": \"Puerto Vallarta\",\n" +
            "\"distance\": 53\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Acapulco\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 36\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Leon\",\n" +
            "\"to\": \"Zacatecas\",\n" +
            "\"distance\": 45\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"La Paz\",\n" +
            "\"to\": \"Durango\",\n" +
            "\"distance\": 38\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"La Paz\",\n" +
            "\"to\": \"Tlaxcala\",\n" +
            "\"distance\": 38\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Cuautitlan\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 28\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Pachuca\",\n" +
            "\"to\": \"San Luis Potosi\",\n" +
            "\"distance\": 12\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Cuernavaca\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 48\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Durango\",\n" +
            "\"to\": \"San Luis Potosi\",\n" +
            "\"distance\": 21\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"La Paz\",\n" +
            "\"to\": \"Cdmx\",\n" +
            "\"distance\": 52\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Ensenada\",\n" +
            "\"to\": \"Tepic\",\n" +
            "\"distance\": 87\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"San Luis Potosi\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 8\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Saltillo\",\n" +
            "\"to\": \"Leon\",\n" +
            "\"distance\": 62\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Queretaro\",\n" +
            "\"to\": \"Cuautitlan\",\n" +
            "\"distance\": 22\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Tuxtla Gutierrez\",\n" +
            "\"to\": \"Tampico\",\n" +
            "\"distance\": 78\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Lazaro Cardenas\",\n" +
            "\"to\": \"Ensenada\",\n" +
            "\"distance\": 77\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Chihuahua\",\n" +
            "\"to\": \"Saltillo\",\n" +
            "\"distance\": 58\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Cdmx\",\n" +
            "\"to\": \"Puebla\",\n" +
            "\"distance\": 58\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Queretaro\",\n" +
            "\"to\": \"Cancun\",\n" +
            "\"distance\": 21\n" +
            "},\n" +
            "{\n" +
            "\"from\": \"Queretaro\",\n" +
            "\"to\": \"Ensenada\",\n" +
            "\"distance\": 82\n" +
            "}\n" +
            "]";
    private static final String ORIGIN = "Chihuahua";
    private static final String DESTINATION = "Cancun";
    private List<String> shortestPath = Collections.unmodifiableList(Arrays.asList("Chihuahua","La Paz","Durango","San Luis Potosi","Cancun"));

    @Before
    public void setup() {
        amqpTemplateMock = mock(RabbitTemplate.class);
        globalProperties = new GlobalProperties();
        queueSender = new QueueSender(amqpTemplateMock, globalProperties);
        shippingService = new ShippingServiceImpl(queueSender);
        shippingPayload = new ShippingPayload();
        shippingPayload.setOrigin(ORIGIN);
        shippingPayload.setDestination(DESTINATION);
    }

    @Test
    public void getPackageSizeTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, PACKAGE_SIZE_REQUEST)).thenReturn(PACKAGE_SIZE_RESPONSE);
        List<String> sizes = shippingService.getPackageSize();
        assertEquals(sizes.size(), 3);
    }

    @Test
    public void getPackageSizeElementsTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, PACKAGE_SIZE_REQUEST)).thenReturn(PACKAGE_SIZE_RESPONSE);
        List<String> sizes = shippingService.getPackageSize();
        assertThat(sizes, hasItems("Small", "Medium", "Large"));
    }

    @Test(expected = QueueException.class)
    public void getPackageSizeExceptionTest(){
        when(amqpTemplateMock.convertSendAndReceive(null, null, PACKAGE_SIZE_REQUEST)).thenThrow(QueueException.class);
        shippingService.getPackageSize();
    }

    @Test
    public void getPackageTypeTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, PACKAGE_TYPE_REQUEST)).thenReturn(PACKAGE_TYPE_RESPONSE);
        List<String> types = shippingService.getPackageType();
        assertEquals(types.size(), 2);
    }

    @Test
    public void getPackageTypeElementsTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, PACKAGE_TYPE_REQUEST)).thenReturn(PACKAGE_TYPE_RESPONSE);
        List<String> types = shippingService.getPackageType();
        assertThat(types, hasItems("Box", "Envelope"));
    }

    @Test(expected = QueueException.class)
    public void getPackageTypeExceptionTest(){
        when(amqpTemplateMock.convertSendAndReceive(null, null, PACKAGE_TYPE_REQUEST)).thenThrow(QueueException.class);
        shippingService.getPackageType();
    }

    @Test
    public void getTransportVelocityTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, TRANSPORT_VELOCITY_REQUEST)).thenReturn(TRANSPORT_VELOCITY_RESPONSE);
        List<String> times = shippingService.getTransportVelocity();
        assertEquals(times.size(), 3);
    }

    @Test
    public void getTransportVelocityElementsTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, TRANSPORT_VELOCITY_REQUEST)).thenReturn(TRANSPORT_VELOCITY_RESPONSE);
        List<String> times = shippingService.getTransportVelocity();
        assertThat(times, hasItems("Regular", "Express", "Slow"));
    }

    @Test(expected = QueueException.class)
    public void getTransportVelocityExceptionTest(){
        when(amqpTemplateMock.convertSendAndReceive(null, null, TRANSPORT_VELOCITY_REQUEST)).thenThrow(QueueException.class);
        shippingService.getTransportVelocity();
    }

    @Test
    public void getTransportTypeTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, TRANSPORT_TYPE_REQUEST)).thenReturn(TRANSPORT_TYPE_RESPONSE);
        List<String> transports = shippingService.getTransportType();
        assertEquals(transports.size(), 2);
    }

    @Test
    public void geTransportTypeElementsTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, TRANSPORT_TYPE_REQUEST)).thenReturn(TRANSPORT_TYPE_RESPONSE);
        List<String> transports = shippingService.getTransportType();
        assertThat(transports, hasItems("Land", "Air"));
    }

    @Test(expected = QueueException.class)
    public void geTransportTypeExceptionTest(){
        when(amqpTemplateMock.convertSendAndReceive(null, null, TRANSPORT_TYPE_REQUEST)).thenThrow(QueueException.class);
        shippingService.getTransportType();
    }

    @Test
    public void getCityTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, CITY_REQUEST)).thenReturn(CITY_RESPONSE);
        List<String> cities = shippingService.getCity();
        assertEquals(cities.size(), 32);
    }

    @Test
    public void getCityElementsTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, CITY_REQUEST)).thenReturn(CITY_RESPONSE);
        List<String> cities = shippingService.getCity();
        assertThat(cities, hasItems("Leon", "Cuernavaca", "Tuxtla Gutierrez", "Veracruz", "Villahermosa", "Saltillo",
                "Tampico", "Cancun", "Ciudad del Carmen", "Merida", "Durango", "Chihuahua", "Cuautitlan", "Ensenada",
                "San Luis Potosi", "Cdmx", "Zacatecas", "Pachuca", "Aguascalientes", "Puebla", "Queretaro", "La Paz",
                "Guaymas", "Mazatlan", "Tepic", "Puerto Vallarta", "Manzanillo", "Lazaro Cardenas", "Acapulco",
                "Puerto Escondido", "Tlaxcala", "Monterrey"));
    }

    @Test
    public void getCityElementsOrderTest(){
        when(amqpTemplateMock.convertSendAndReceive(null, null, CITY_REQUEST)).thenReturn(CITY_RESPONSE);
        List<String> cities = shippingService.getCity();
        assertThat(cities, contains("Acapulco", "Aguascalientes", "Cancun", "Cdmx", "Chihuahua",
                "Ciudad del Carmen", "Cuautitlan", "Cuernavaca", "Durango", "Ensenada", "Guaymas", "La Paz", "Lazaro Cardenas",
                "Leon", "Manzanillo", "Mazatlan", "Merida", "Monterrey", "Pachuca", "Puebla", "Puerto Escondido",
                "Puerto Vallarta", "Queretaro", "Saltillo", "San Luis Potosi", "Tampico", "Tepic", "Tlaxcala",
                "Tuxtla Gutierrez", "Veracruz", "Villahermosa", "Zacatecas"));
    }

    @Test(expected = QueueException.class)
    public void getCityExceptionTest(){
        when(amqpTemplateMock.convertSendAndReceive(null, null, CITY_REQUEST)).thenThrow(QueueException.class);
        shippingService.getCity();
    }

    @Test
    public void getRouteTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, ROUTE_REQUEST)).thenReturn(ROUTE_RESPONSE);
        List<String> routes = shippingService.getShippingInformation(shippingPayload).getPath();
        assertEquals(routes.size(), 5);
    }

    @Test
    public void getRouteElementsTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, ROUTE_REQUEST)).thenReturn(ROUTE_RESPONSE);
        List<String> routes = shippingService.getShippingInformation(shippingPayload).getPath();
        assertThat(routes, equalTo(shortestPath));
    }

    @Test(timeout = 1000)
    public void getRouteTimeTest(){
        when(amqpTemplateMock.convertSendAndReceive(null, null, ROUTE_REQUEST)).thenReturn(ROUTE_RESPONSE);
        List<String> routes = shippingService.getShippingInformation(shippingPayload).getPath();
        assertThat(routes, equalTo(shortestPath));
    }

    @Test(expected = QueueException.class)
    public void getRouteVelocityExceptionTest() {
        when(amqpTemplateMock.convertSendAndReceive(null, null, ROUTE_REQUEST)).thenThrow(QueueException.class);
        shippingService.getShippingInformation(shippingPayload);
    }
}