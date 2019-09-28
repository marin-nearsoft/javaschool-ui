package com.nearsoft.javaschoolbackend.controller;

import com.nearsoft.javaschoolbackend.service.PackageInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/shipping-api/package-info")
@AllArgsConstructor
public class PackageInfoController {

    private final PackageInfoService packageInfoService;

    @GetMapping("/package-types")
    public List<String> getPackageTypes(){
        return packageInfoService.getPackageDescriptionTypes();
    }

    @GetMapping("/package-sizes")
    public List<String> getPackageSizes(){
        return packageInfoService.getPackageDescriptionSizes();
    }

    @GetMapping("/transport-types")
    public List<String> getTransportTypes(){
        return packageInfoService.getTransportDescriptionTypes();
    }

    @GetMapping("/transport-velocities")
    public List<String> getTransportVelocities(){
        return packageInfoService.getTransportDescriptionVelocities();
    }

}