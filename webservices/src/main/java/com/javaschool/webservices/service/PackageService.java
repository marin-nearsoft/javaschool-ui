package com.javaschool.webservices.service;

import java.util.List;

public interface PackageService {
	
	List<String> getSizes();
	
	List<String> getTypes();
	
	List<String> getTransports();

	List<String> getTransportVelocities();

	List<String> getCities();

}
