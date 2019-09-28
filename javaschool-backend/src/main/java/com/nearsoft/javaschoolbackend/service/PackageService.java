package com.nearsoft.javaschoolbackend.service;

import com.nearsoft.javaschoolbackend.model.response.*;

import java.util.List;

public interface PackageService {

    List<PackageType> getPackageTypes();

    List<PackageSize> getPackageSizes();

    List<TransportType> getTransportTypes();

    List<TransportVelocity> getTransportVelocities();

    List<City> getCities();

}
