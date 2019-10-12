package com.javaschool.service;


import com.javaschool.common.ShippingInformation;
import com.javaschool.common.ShippingPayload;

import java.util.List;

public interface ShippingService {

    List<String> getPackageSize();

    List<String> getPackageType();

    List<String> getTransportVelocity();

    List<String> getTransportType();

    List<String> getCity();

    ShippingInformation getShippingInformation(ShippingPayload shippingPayload);
}
