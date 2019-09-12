package com.javaschool.webservices.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaschool.webservices.model.PackageType;
import com.javaschool.webservices.service.PackageService;
import com.javaschool.webservices.service.ShippingService;

@Service
public class PackageServiceImpl implements PackageService {
	
	private final ShippingService shippingService;
	
	public PackageServiceImpl(ShippingService shippingProducerService) {
		this.shippingService = shippingProducerService;
	}

	@Override
	public List<String> getSizes() {
		List<String> sizesList = new LinkedList<>();
		
		sizesList.add("SMALL");
		sizesList.add("MEDIUM");		
		sizesList.add("LARGE");
		sizesList.add("EXTRA LARGE");
		
		return sizesList;
	}

	@Override
	public List<String> getTypes() {
		List<PackageType> packageTypes = shippingService.getPackageTypes();
		
		List<String> packageTypesNames = new LinkedList<String>(); 

		packageTypes.forEach(packageType->packageTypesNames.add(packageType.getDescription()));
		
		return packageTypesNames;
	}

}