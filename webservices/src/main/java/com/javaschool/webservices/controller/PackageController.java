package com.javaschool.webservices.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaschool.webservices.service.PackageService;

@RestController
@RequestMapping("package-information")
public class PackageController {

	private PackageService packageService;

	public PackageController(PackageService packageService) {
		this.packageService = packageService;
	}

	@GetMapping("/sizes")
	public List<String> getSizes() {
		return packageService.getSizes();
	}

	@GetMapping("/types")
	public List<String> getTypes() {
		return packageService.getTypes();
	}

	@GetMapping("/transports")
	public List<String> getTransports() {
		return packageService.getTransports();
	}

}