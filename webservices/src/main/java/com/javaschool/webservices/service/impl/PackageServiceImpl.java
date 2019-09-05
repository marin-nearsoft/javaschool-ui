package com.javaschool.webservices.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaschool.webservices.service.PackageService;

@Service
public class PackageServiceImpl implements PackageService {

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
		List<String> typesList = new LinkedList<String>(); 

		typesList.add("BOX");
		typesList.add("LETTER");
		typesList.add("OTHER");
		
		return typesList;
	}

}