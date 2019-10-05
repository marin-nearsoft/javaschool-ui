package com.javaschool.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.entitymapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class QueueResponseServiceImp implements QueueResponseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSenderServiceImp.class);

    private QueueSenderService queueSenderService;
    private ObjectMapper mapper;


    public QueueResponseServiceImp(final QueueSenderService queueSenderService, final ObjectMapper mapper) {
        this.queueSenderService = queueSenderService;
        this.mapper = mapper;
    }

    @Override
    public List<PackageType> getType() {
        List<PackageType> types = Collections.emptyList();
        try {
            String response = queueSenderService.sendRequest("packageType");
            types = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, PackageType.class));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return types;
    }

    @Override
    public List<PackageSize> getSize() {
        List<PackageSize> sizes = Collections.emptyList();
        try {
            String response = queueSenderService.sendRequest("packageSize");
            sizes = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, PackageSize.class));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return sizes;
    }

    @Override
    public List<TransportType> getTransport() {
        List<TransportType> transports = Collections.emptyList();
        try {
            String response = queueSenderService.sendRequest("transportType");
            transports = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, TransportType.class));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return transports;
    }

    @Override
    public List<TransportVelocity> getVelocity() {
        List<TransportVelocity> velocities = Collections.emptyList();
        try {
            String response = queueSenderService.sendRequest("transportVelocity");
            velocities = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, TransportVelocity.class));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return velocities;
    }

    @Override
    public List<Cities> getCity() {
        List<Cities> cities = Collections.emptyList();
        try {
            String response = queueSenderService.sendRequest("city");
            cities = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Cities.class));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cities;
    }
}
