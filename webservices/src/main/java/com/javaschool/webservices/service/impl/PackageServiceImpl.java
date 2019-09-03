package com.javaschool.webservices.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaschool.webservices.service.PackageService;

@Service
public class PackageServiceImpl implements PackageService {

	@Override
	public List<String> getSizes() {
		List<String> list = new LinkedList<>();
		list.add("test1");
		list.add("test2");		
		
		return list;
	}

}
