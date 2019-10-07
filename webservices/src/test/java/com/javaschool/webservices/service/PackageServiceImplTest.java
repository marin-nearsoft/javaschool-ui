package com.javaschool.webservices.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.javaschool.webservices.model.City;
import com.javaschool.webservices.model.PackageSize;
import com.javaschool.webservices.model.TransportVelocity;
import com.javaschool.webservices.model.PackageTransport;
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
	
	@Test
	public void testTransports() {
		List<PackageTransport> packageTransportsList = new LinkedList<PackageTransport>();
		
		PackageTransport packageTransport1 = new PackageTransport();
		packageTransport1.setId(1);
		packageTransport1.setDescription("Package transport 1 test");
		packageTransport1.setPricePerMile(10.10);
		
		PackageTransport packageTransport2 = new PackageTransport();
		packageTransport2.setId(2);
		packageTransport2.setDescription("Package transport 2 test");
		packageTransport2.setPricePerMile(20.20);
		
		PackageTransport packageTransport3 = new PackageTransport();
		packageTransport3.setId(3);
		packageTransport3.setDescription("Package transport 3 test");
		packageTransport3.setPricePerMile(30.30);
		
		packageTransportsList.add(packageTransport1);
		packageTransportsList.add(packageTransport2);
		packageTransportsList.add(packageTransport3);
		
		Mockito.when(shippingServiceMock.getPackageTransport()).thenReturn(packageTransportsList);
		
		List<String> packageTransportsNames = packageService.getTransports();
		
		assertNotNull(packageTransportsNames);
		assertEquals(packageTransport1.getDescription(), packageTransportsNames.get(0));
		assertEquals(packageTransport2.getDescription(), packageTransportsNames.get(1));
		assertEquals(packageTransport3.getDescription(), packageTransportsNames.get(2));
	}
	
	@Test
	public void testTransporVelocities() {
		List<TransportVelocity> packageTimesList = new LinkedList<TransportVelocity>();
		
		TransportVelocity packageTime1 = new TransportVelocity();
		packageTime1.setId(1);
		packageTime1.setDescription("Package time 1 test");
		packageTime1.setPriceFactor(10.10);
		
		TransportVelocity packageTime2 = new TransportVelocity();
		packageTime2.setId(1);
		packageTime2.setDescription("Package time 2 test");
		packageTime2.setPriceFactor(20.20);
		
		TransportVelocity packageTime3 = new TransportVelocity();
		packageTime3.setId(3);
		packageTime3.setDescription("Package time 3 test");
		packageTime3.setPriceFactor(30.30);
		
		packageTimesList.add(packageTime1);
		packageTimesList.add(packageTime2);
		packageTimesList.add(packageTime3);
		
		Mockito.when(shippingServiceMock.getTransportVelocities()).thenReturn(packageTimesList);
		
		List<String> packageTimeNames = packageService.getTransportVelocities();
		
		assertEquals(3, packageTimeNames.size());
		assertEquals(packageTime1.getDescription(), packageTimeNames.get(0));
		assertEquals(packageTime2.getDescription(), packageTimeNames.get(1));
		assertEquals(packageTime3.getDescription(), packageTimeNames.get(2));
	}
	
	@Test
	public void testCitiesOrder() {
		List<City> citiesList = new LinkedList<City>();
		
		City city1 = new City();
		city1.setId(1);
		city1.setName("Leon");
		
		City city2 = new City();
		city2.setId(2);
		city2.setName("Chihuahua");
		
		City city3 = new City();
		city3.setId(3);
		city3.setName("Cancun");
		
		citiesList.add(city1);
		citiesList.add(city2);
		citiesList.add(city3);
		
		Mockito.when(shippingServiceMock.getCities()).thenReturn(citiesList);
		
		List<String> citiesNamesList = packageService.getCities();
		
		assertEquals(3, citiesList.size());
		assertEquals(city3.getName(), citiesNamesList.get(0));
		assertEquals(city2.getName(), citiesNamesList.get(1));
		assertEquals(city1.getName(), citiesNamesList.get(2));
		
	}

}
