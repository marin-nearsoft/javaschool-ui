package com.javaschool.webservices.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.javaschool.webservices.service.impl.PackageServiceImpl;
import com.javaschool.webservices.service.impl.ShippingRabbitRPCServiceImpl;

public class PackageServiceImplTest {
	
	private PackageService packageService;
	
	@Before
	public void setUp() {
		packageService = new PackageServiceImpl(new ShippingRabbitRPCServiceImpl(new RabbitTemplate(), null, null, null));
	}

	@Test
	public void testSizes() {
		assertNotNull(packageService.getSizes());
	}
	
	@Test
	public void testTypes() {
		assertNotNull(packageService.getTypes());
	}

}
