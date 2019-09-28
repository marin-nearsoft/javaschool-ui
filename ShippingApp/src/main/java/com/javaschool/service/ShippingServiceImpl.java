package com.javaschool.service;

import com.javaschool.common.*;
import com.javaschool.queue.QueueSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingServiceImpl implements ShippingService {

    private static final Logger logger = LoggerFactory.getLogger(ShippingServiceImpl[].class);
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
        List<PackageSize> packageSizes;
        List<String> sizes;
        try {
            packageSizes = queueSender.messageRequest(PACKAGE_SIZE, new PackageSize[0]);
            sizes = packageSizes.stream()
                    .map(size -> size.getDescription())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return sizes;
    }

    @Override
    public List<String> getPackageType() {
        List<PackageType> packageTypes;
        List<String> types;
        try {
            packageTypes = queueSender.messageRequest(PACKAGE_TYPE, new PackageType[0]);
            types = packageTypes.stream()
                    .map(type -> type.getDescription())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return types;
    }

    @Override
    public List<String> getTransportVelocity() {
        List<TransportVelocity> transportVelocities;
        List<String> velocities;
        try {
            transportVelocities = queueSender.messageRequest(TRANSPORT_VELOCITY, new TransportVelocity[0]);
            velocities = transportVelocities.stream()
                    .map(velocity -> velocity.getDescription())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return velocities;
    }

    @Override
    public List<String> getTransportType() {
        List<TransportType> transportTypes;
        List<String> types;
        try {
            transportTypes = queueSender.messageRequest(TRANSPORT_TYPE, new TransportType[0]);
            types = transportTypes.stream()
                    .map(type -> type.getDescription())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return types;
    }

    @Override
    public List<String> getCity(){
        List<City> cities;
        List<String> cityList;
        try {
            cities = queueSender.messageRequest(CITY, new City[0]);
            cityList = cities.stream()
                    .map(type -> type.getName())
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return cityList;
    }
}
