package com.javaschool.service;


import java.util.List;

public interface ShippingService {

    List<String> getPackageSize();

    List<String> getPackageType();

    List<String> getTransportVelocity();

    List<String> getTransportType();

    List<String> getCity();

    List<String> getRoute();
}
