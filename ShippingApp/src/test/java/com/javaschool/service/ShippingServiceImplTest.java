package com.javaschool.service;

import com.javaschool.common.GlobalProperties;
import com.javaschool.common.QueueException;
import com.javaschool.queue.QueueSender;
import org.hamcrest.collection.IsIterableContainingInOrder;
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

    @Test
    public void getTransportVelocityTest() {
        String queueResponse = "[{\"id\":1,\"description\":\"Regular\",\"priceFactor\":5},{\"id\":2,\"description\":\"Express\",\"priceFactor\":10},{\"id\":3,\"description\":\"Slow\",\"priceFactor\":0}]";
        String queueMessage = "{\"type\":\"transportVelocity\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> times = shippingService.getTransportVelocity();
        assertEquals(times.size(), 3);
    }

    @Test
    public void getTransportVelocityElementsTest() {
        String queueResponse = "[{\"id\":1,\"description\":\"Regular\",\"priceFactor\":5},{\"id\":2,\"description\":\"Express\",\"priceFactor\":10},{\"id\":3,\"description\":\"Slow\",\"priceFactor\":0}]";
        String queueMessage = "{\"type\":\"transportVelocity\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> times = shippingService.getTransportVelocity();
        assertThat(times, hasItems("Regular", "Express", "Slow"));
    }

    @Test(expected = QueueException.class)
    public void getTransportVelocityExceptionTest(){
        String queueMessage = "{\"type\":\"transportVelocity\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(null);
        List<String> times = shippingService.getTransportVelocity();
    }

    @Test
    public void getTransportTypeTest() {
        String queueResponse = "[{\"id\":2,\"description\":\"Land\",\"pricePerMile\":2},{\"id\":1,\"description\":\"Air\",\"pricePerMile\":5}]";
        String queueMessage = "{\"type\":\"transportType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> transports = shippingService.getTransportType();
        assertEquals(transports.size(), 2);
    }

    @Test
    public void getransportTypeElementsTest() {
        String queueResponse = "[{\"id\":2,\"description\":\"Land\",\"pricePerMile\":2},{\"id\":1,\"description\":\"Air\",\"pricePerMile\":5}]";
        String queueMessage = "{\"type\":\"transportType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> transports = shippingService.getTransportType();
        assertThat(transports, hasItems("Land", "Air"));
    }

    @Test(expected = QueueException.class)
    public void getransportTypeExceptionTest(){
        String queueMessage = "{\"type\":\"transportType\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(null);
        shippingService.getTransportType();
    }

    @Test
    public void getCityTest() {
        String queueResponse = "[{\"id\":9,\"name\":\"Leon\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":12," +
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
        String queueMessage = "{\"type\":\"city\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> cities = shippingService.getCity();
        assertEquals(cities.size(), 32);
    }

    @Test
    public void getCityElementsTest() {
        String queueResponse = "[{\"id\":9,\"name\":\"Leon\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":12," +
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
        String queueMessage = "{\"type\":\"city\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> cities = shippingService.getCity();
        assertThat(cities, hasItems("Leon", "Cuernavaca", "Tuxtla Gutierrez", "Veracruz", "Villahermosa", "Saltillo",
                "Tampico", "Cancun", "Ciudad del Carmen", "Merida", "Durango", "Chihuahua", "Cuautitlan", "Ensenada",
                "San Luis Potosi", "Cdmx", "Zacatecas", "Pachuca", "Aguascalientes", "Puebla", "Queretaro", "La Paz",
                "Guaymas", "Mazatlan", "Tepic", "Puerto Vallarta", "Manzanillo", "Lazaro Cardenas", "Acapulco",
                "Puerto Escondido", "Tlaxcala", "Monterrey"));
    }

    @Test
    public void getCityElementsOrderTest(){
        String queueResponse = "[{\"id\":9,\"name\":\"Leon\",\"tax\":10,\"seaport\":false,\"airport\":false},{\"id\":12," +
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
        String queueMessage = "{\"type\":\"city\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(queueResponse);
        List<String> cities = shippingService.getCity();
        assertThat(cities, IsIterableContainingInOrder.contains("Acapulco", "Aguascalientes", "Cancun", "Cdmx", "Chihuahua",
                "Ciudad del Carmen", "Cuautitlan", "Cuernavaca", "Durango", "Ensenada", "Guaymas", "La Paz", "Lazaro Cardenas",
                "Leon", "Manzanillo", "Mazatlan", "Merida", "Monterrey", "Pachuca", "Puebla", "Puerto Escondido",
                "Puerto Vallarta", "Queretaro", "Saltillo", "San Luis Potosi", "Tampico", "Tepic", "Tlaxcala",
                "Tuxtla Gutierrez", "Veracruz", "Villahermosa", "Zacatecas"));
    }

    @Test(expected = QueueException.class)
    public void getCityExceptionTest(){
        String queueMessage = "{\"type\":\"city\"}";
        when(amqpTemplateMock.convertSendAndReceive(null, null, queueMessage)).thenReturn(null);
        shippingService.getCity();
    }
}