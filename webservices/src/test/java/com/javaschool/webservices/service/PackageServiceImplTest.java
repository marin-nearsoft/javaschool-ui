package com.javaschool.webservices.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.javaschool.webservices.service.impl.PackageServiceImpl;

public class PackageServiceImplTest {
	
	private PackageService packageService;
	
	@Before
	public void setUp() {
		packageService = new PackageServiceImpl();
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
