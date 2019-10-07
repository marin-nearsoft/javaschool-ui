package com.javaschool.webservices.service;

import java.util.List;

import com.javaschool.webservices.model.PackageSize;
import com.javaschool.webservices.model.TransportVelocity;
import com.javaschool.webservices.model.PackageTransport;
import com.javaschool.webservices.model.PackageType;

public interface PackageRabbitMqService {
	
	List<PackageType> getPackageTypes();
	
	List<PackageSize> getPackageSizes();
	
	List<PackageTransport> getPackageTransport();
	
	List<TransportVelocity> getTransportVelocities();

}
