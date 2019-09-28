package com.javaschool.service;

import com.javaschool.common.PackageSize;
import com.javaschool.common.PackageType;
import com.javaschool.common.QueueException;
import com.javaschool.common.TransportVelocity;
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
    public List<String> getTransport() {
        return null;
    }
}
