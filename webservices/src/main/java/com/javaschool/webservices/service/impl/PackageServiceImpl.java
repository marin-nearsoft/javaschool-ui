package com.javaschool.webservices.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaschool.webservices.model.City;
import com.javaschool.webservices.model.PackageSize;
import com.javaschool.webservices.model.TransportVelocity;
import com.javaschool.webservices.model.PackageTransport;
import com.javaschool.webservices.model.PackageType;
import com.javaschool.webservices.service.PackageRabbitMqService;
import com.javaschool.webservices.service.PackageService;

@Service
public class PackageServiceImpl implements PackageService {

	private final PackageRabbitMqService shippingService;

	public PackageServiceImpl(PackageRabbitMqService shippingProducerService) {
		this.shippingService = shippingProducerService;
	}

	@Override
	public List<String> getSizes() {
		List<PackageSize> packageSizes = shippingService.getPackageSizes();

		return packageSizes.stream().map(packageSize -> packageSize.getDescription())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getTypes() {
		List<PackageType> packageTypes = shippingService.getPackageTypes();

		return packageTypes.stream().map(packageType -> packageType.getDescription())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getTransports() {
		List<PackageTransport> packageTransports = shippingService.getPackageTransport();
		
		return packageTransports.stream().map(packageTransport -> packageTransport.getDescription()).
				collect(Collectors.toList());
	}
	
	@Override
	public List<String> getTransportVelocities() {
		List<TransportVelocity> packageTransports = shippingService.getTransportVelocities();
		
		return packageTransports.stream().map(packageTime -> packageTime.getDescription()).
				collect(Collectors.toList());
	}

	@Override
	public List<String> getCities() {
		List<City> cities = shippingService.getCities();
		
		return  cities.stream().
				sorted(Comparator.comparing(City::getName)).
				map(city -> city.getName()).
				collect(Collectors.toList());
	}
}