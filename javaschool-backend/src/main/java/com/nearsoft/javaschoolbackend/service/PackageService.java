package com.nearsoft.javaschoolbackend.service;

import com.nearsoft.javaschoolbackend.model.response.PackageSize;
import com.nearsoft.javaschoolbackend.model.response.PackageType;
import com.nearsoft.javaschoolbackend.model.response.TransportType;
import com.nearsoft.javaschoolbackend.model.response.TransportVelocity;

import java.util.List;

public interface PackageService {

    List<PackageType> getPackageTypes();

    List<PackageSize> getPackageSizes();

    List<TransportType> getTransportTypes();

    List<TransportVelocity> getTransportVelocities();

}
