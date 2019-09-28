package com.nearsoft.javaschoolbackend.service.impl;

import com.nearsoft.javaschoolbackend.service.PackageInfoService;
import com.nearsoft.javaschoolbackend.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PackageInfoServiceImpl implements PackageInfoService {

    private final PackageService packageService;

    @Override
    public List<String> getPackageDescriptionTypes() {
        return packageService.getPackageTypes().stream().map(type -> type.getDescription()).collect(Collectors.toList());
    }

    @Override
    public List<String> getPackageDescriptionSizes() {
        return packageService.getPackageSizes().stream().map(size -> size.getDescription()).collect(Collectors.toList());
    }

    @Override
    public List<String> getTransportDescriptionTypes() {
        return packageService.getTransportTypes().stream().map(type -> type.getDescription()).collect(Collectors.toList());
    }

    @Override
    public List<String> getTransportDescriptionVelocities() {
        return packageService.getTransportVelocities().stream().map(velocity -> velocity.getDescription()).collect(Collectors.toList());
    }

    @Override
    public List<String> getCityNames() {
        return packageService.getCities().stream().map(city -> city.getName()).collect(Collectors.toList());
    }

}
