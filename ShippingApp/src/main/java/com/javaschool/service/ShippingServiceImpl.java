package com.javaschool.service;

import com.javaschool.common.*;
import com.javaschool.queue.QueueSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingServiceImpl implements ShippingService {

    private QueueSender queueSender;
    private static final String PACKAGE_SIZE = "packageSize";
    private static final String PACKAGE_TYPE = "packageType";
    private static final String TRANSPORT_VELOCITY = "transportVelocity";
    private static final String TRANSPORT_TYPE = "transportType";
    private static final String CITY = "city";

    public ShippingServiceImpl(final QueueSender queueSender) {
        this.queueSender = queueSender;
    }

    @Override
    public List<String> getPackageSize() {
        List<PackageSize> packageSizes = queueSender.messageRequest(PACKAGE_SIZE, new PackageSize[0]);
        List<String> sizes = packageSizes.stream()
                                         .map(PackageSize::getDescription)
                                         .collect(Collectors.toList());
        return sizes;
    }

    @Override
    public List<String> getPackageType() {
        List<PackageType> packageTypes = queueSender.messageRequest(PACKAGE_TYPE, new PackageType[0]);
        List<String> types = packageTypes.stream()
                                         .map(PackageType::getDescription)
                                         .collect(Collectors.toList());
        return types;
    }

    @Override
    public List<String> getTransportVelocity() {
        List<TransportVelocity> transportVelocities = queueSender.messageRequest(TRANSPORT_VELOCITY, new TransportVelocity[0]);
        List<String> velocities = transportVelocities.stream()
                                                     .map(TransportVelocity::getDescription)
                                                     .collect(Collectors.toList());
        return velocities;
    }

    @Override
    public List<String> getTransportType() {
        List<TransportType> transportTypes = queueSender.messageRequest(TRANSPORT_TYPE, new TransportType[0]);
        List<String> types = transportTypes.stream()
                                           .map(TransportType::getDescription)
                                           .collect(Collectors.toList());
        return types;
    }

    @Override
    public List<String> getCity() {
        List<City> cities = queueSender.messageRequest(CITY, new City[0]);
        List<String> cityList = cities.stream()
                                      .map(City::getName)
                                      .sorted()
                                      .collect(Collectors.toList());
        return cityList;
    }
}
