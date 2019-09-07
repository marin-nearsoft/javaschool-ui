package com.javaschool.webservices.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaschool.webservices.service.PackageService;

@RestController
@RequestMapping("packageInformation")
public class PackageController {	
	
	private PackageService packageService;
	
	public PackageController(PackageService packageService) {
		this.packageService = packageService;
	}
		
	@GetMapping("/getSizes")
	public List<String> getSizes() {
		return packageService.getSizes();
	}
	
	@GetMapping("/getTypes")
	public List<String> getTypes(){
		return packageService.getTypes();
	}

}