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
        List<String> res;
        res = packageService.getPackageTypes().stream().map(type -> type.getDescription()).collect(Collectors.toList());
        return res;
    }

    @Override
    public List<String> getPackageDescriptionSizes() {
        List<String> res;
        res = packageService.getPackageSizes().stream().map(size -> size.getDescription()).collect(Collectors.toList());
        return res;
    }
}
