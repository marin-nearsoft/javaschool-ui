package com.nearsoft.javaschoolbackend.controller;

import com.nearsoft.javaschoolbackend.service.PackageInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/shippingAPI/packageInfo")
public class PackageInfoController {

    private final PackageInfoService packageInfoService;

    public PackageInfoController(PackageInfoService packageInfoService){
        this.packageInfoService = packageInfoService;
    }

    @GetMapping("/types")
    public List<String> getPackageTypes(){
        return packageInfoService.getPackageTypes();
    }

    @GetMapping("/sizes")
    public List<String> getPackageSizes(){
        return packageInfoService.getPackageSizes();
    }

}