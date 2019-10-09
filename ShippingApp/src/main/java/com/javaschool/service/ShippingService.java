package com.javaschool.service;


import com.javaschool.common.Route;

import java.util.List;

public interface ShippingService {

    List<String> getPackageSize();

    List<String> getPackageType();

    List<String> getTransportVelocity();

    List<String> getTransportType();

    List<String> getCity();

    List<Route> getRoute();
}
