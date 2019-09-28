package com.nearsoft.javaschoolbackend.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nearsoft.javaschoolbackend.exception.custom.PackageDataException;
import com.nearsoft.javaschoolbackend.model.response.PackageSize;
import com.nearsoft.javaschoolbackend.model.response.PackageType;
import com.nearsoft.javaschoolbackend.model.response.TransportType;
import com.nearsoft.javaschoolbackend.model.response.TransportVelocity;
import com.nearsoft.javaschoolbackend.service.PackageService;
import com.nearsoft.javaschoolbackend.util.RabbitMQSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final RabbitMQSender rabbitMQSender;

    private final ObjectMapper objectMapper;

    @Override
    public List<PackageType> getPackageTypes() {
        List<PackageType> packageTypes;

        try {
            packageTypes = objectMapper.readValue(rabbitMQSender.send("packageType"), new TypeReference<List<PackageType>>(){});
        } catch (IOException | NullPointerException e) {
            log.error(e.getMessage());
            throw new PackageDataException("JSON mapping error occurred");
        }

        return packageTypes;
    }

    @Override
    public List<PackageSize> getPackageSizes() {
        List<PackageSize> packageSizes;

        try {
            packageSizes = objectMapper.readValue(rabbitMQSender.send("packageSize"), new TypeReference<List<PackageSize>>(){});
        } catch (IOException | NullPointerException e) {
            log.error(e.getMessage());
            throw new PackageDataException("JSON mapping error occurred");
        }

        return packageSizes;
    }

    @Override
    public List<TransportType> getTransportTypes() {
        List<TransportType> transportTypes;

        try {
            transportTypes = objectMapper.readValue(rabbitMQSender.send("transportType"), new TypeReference<List<TransportType>>(){});
        } catch (IOException | NullPointerException e) {
            log.error(e.getMessage());
            throw new PackageDataException("JSON mapping error occurred");
        }

        return transportTypes;
    }

    @Override
    public List<TransportVelocity> getTransportVelocities() {
        List<TransportVelocity> transportVelocities;

        try {
            transportVelocities = objectMapper.readValue(rabbitMQSender.send("transportVelocity"), new TypeReference<List<TransportVelocity>>(){});
        } catch (IOException | NullPointerException e) {
            log.error(e.getMessage());
            throw new PackageDataException("JSON mapping error occurred");
        }

        return transportVelocities;
    }

}
