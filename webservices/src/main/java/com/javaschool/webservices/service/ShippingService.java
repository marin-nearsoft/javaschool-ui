package com.javaschool.webservices.service;

import java.util.List;

import com.javaschool.webservices.model.PackageSize;
import com.javaschool.webservices.model.PackageType;

public interface ShippingService {
	
	List<PackageType> getPackageTypes();
	
	List<PackageSize> getPackageSizes();

}
