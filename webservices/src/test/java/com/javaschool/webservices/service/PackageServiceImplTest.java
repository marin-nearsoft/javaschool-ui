package com.javaschool.webservices.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.javaschool.webservices.model.PackageType;
import com.javaschool.webservices.service.impl.PackageServiceImpl;

public class PackageServiceImplTest {
	
	private PackageService packageService;
	
	private ShippingService shippingServiceMock;
	
	@Before
	public void setUp() {
		shippingServiceMock = Mockito.mock(ShippingService.class);
		packageService = new PackageServiceImpl(shippingServiceMock);
	}

	@Test
	public void testSizes() {
		assertNotNull(packageService.getSizes());
	}
	
	@Test
	public void testTypes() {
		List<PackageType> packageTypesList = new LinkedList<PackageType>();
		
		PackageType packageType1 = new PackageType();
		packageType1.setId(1);
		packageType1.setDescription("Package type 1 test");
		packageType1.setPrice(10);
		packageTypesList.add(packageType1);
		
		PackageType packageType2 = new PackageType();
		packageType2.setId(2);
		packageType2.setDescription("Package type 2 test");
		packageType2.setPrice(20);
		packageTypesList.add(packageType2);
		
		PackageType packageType3 = new PackageType();
		packageType3.setId(3);
		packageType3.setDescription("Package type 3 test");
		packageType3.setPrice(30);
		packageTypesList.add(packageType3);
		
		
		Mockito.when(shippingServiceMock.getPackageTypes()).thenReturn(packageTypesList);
		
		List<String> packageTypesNames = packageService.getTypes();
		
		assertEquals(3, packageTypesNames.size());
	}

}
