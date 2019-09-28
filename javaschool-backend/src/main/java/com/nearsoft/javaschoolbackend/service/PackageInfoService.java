package com.nearsoft.javaschoolbackend.service;

import java.util.List;

public interface PackageInfoService {

    List<String> getPackageDescriptionTypes();

    List<String> getPackageDescriptionSizes();

    List<String> getTransportDescriptionTypes();

    List<String> getTransportDescriptionVelocities();

    List<String> getCityNames();

}
