package com.javaschool.webservices.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.webservices.configuration.RabbitMQProperties;
import com.javaschool.webservices.model.PackageSize;
import com.javaschool.webservices.model.PackageType;
import com.javaschool.webservices.service.impl.ShippingRabbitRPCServiceImpl;

public class ShippingRabbitRPCServiceImplTest {

	private RabbitTemplate rabbitTemplateMock;

	private ShippingService shippingService;

	private RabbitMQProperties rabbitMQProperties;

	@Before
	public void setUp() {
		rabbitTemplateMock = Mockito.mock(RabbitTemplate.class);
		rabbitMQProperties = new RabbitMQProperties();
		rabbitMQProperties.setExchange("exchange_test");
		rabbitMQProperties.setRoutingKey("routing_key_test");
		shippingService = new ShippingRabbitRPCServiceImpl(rabbitTemplateMock, new ObjectMapper(), rabbitMQProperties);
	}

	@Test
	public void testgetPackageTypesWhenServerError() {
		Mockito.when(rabbitTemplateMock.convertSendAndReceive(Mockito.eq(rabbitMQProperties.getExchange()),
				Mockito.eq(rabbitMQProperties.getRoutingKey()), Mockito.any(Object.class)))
				.thenThrow(new AmqpException("Server error"));

		List<PackageType> packageTypesList = shippingService.getPackageTypes();
		assertTrue(packageTypesList.isEmpty());
	}

	@Test
	public void testgetPackageTypes() {
		String jsonRabbitTemplateReponse = "[{\"id\":1,\"description\":\"Package type 1 test\",\"price\":10},{\"id\":2,\"description\":\"Package type 2 test\",\"price\":20},{\"id\":3,\"description\":\"Package type 3 test\",\"price\":30}]";

		Mockito.when(rabbitTemplateMock.convertSendAndReceive(Mockito.eq(rabbitMQProperties.getExchange()),
				Mockito.eq(rabbitMQProperties.getRoutingKey()), Mockito.any(Object.class)))
				.thenReturn(jsonRabbitTemplateReponse);

		List<PackageType> packageTypesList = shippingService.getPackageTypes();
		assertFalse(packageTypesList.isEmpty());
		assertEquals(3, packageTypesList.size());
	}

	@Test
	public void testgetPackageSizes() {
		String jsonRabbitTemplateReponse = "[{\"id\":1,\"description\":\"Small\",\"priceFactor\":10},{\"id\":2,\"description\":\"Medium\",\"priceFactor\":20},{\"id\":3,\"description\":\"Big\",\"priceFactor\":30}]";

		Mockito.when(rabbitTemplateMock.convertSendAndReceive(Mockito.eq(rabbitMQProperties.getExchange()),
				Mockito.eq(rabbitMQProperties.getRoutingKey()), Mockito.any(Object.class)))
				.thenReturn(jsonRabbitTemplateReponse);

		List<PackageSize> packageSizesList = shippingService.getPackageSizes();
		assertFalse(packageSizesList.isEmpty());
		assertEquals(3, packageSizesList.size());
	}
}