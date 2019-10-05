package com.javaschool.webservices.service;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.javaschool.webservices.model.PackageSize;
import com.javaschool.webservices.model.PackageType;
import com.javaschool.webservices.service.impl.PackageServiceImpl;

public class PackageServiceImplTest {
	
	private PackageService packageService;
	
	private PackageRabbitMqService shippingServiceMock;
	
	@Before
	public void setUp() {
		shippingServiceMock = Mockito.mock(PackageRabbitMqService.class);
		packageService = new PackageServiceImpl(shippingServiceMock);
	}

	@Test
	public void testSizes() {
		List<PackageSize> packageSizesList = new LinkedList<PackageSize>();
		
		PackageSize packageSize1 = new PackageSize();
		packageSize1.setId(1);
		packageSize1.setDescription("Small");
		packageSize1.setPriceFactor(10);
		
		PackageSize packageSize2 = new PackageSize();
		packageSize2.setId(2);
		packageSize2.setDescription("Medium");
		packageSize2.setPriceFactor(20.20);
		
		PackageSize packageSize3 = new PackageSize();
		packageSize3.setId(3);
		packageSize3.setDescription("Big");
		packageSize3.setPriceFactor(30.30);
		
		packageSizesList.add(packageSize1);
		packageSizesList.add(packageSize2);
		packageSizesList.add(packageSize3);
		
		Mockito.when(shippingServiceMock.getPackageSizes()).thenReturn(packageSizesList);
		
		List<String> packageSizesNames = packageService.getSizes();
		
		assertEquals(3, packageSizesNames.size());
		
		
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
		assertEquals(packageType1.getDescription(), packageTypesNames.get(0));
		assertEquals(packageType2.getDescription(), packageTypesNames.get(1));
		assertEquals(packageType3.getDescription(), packageTypesNames.get(2));
	}

}
