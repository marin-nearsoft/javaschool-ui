package com.javaschool.queue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.entitymapper.PackageSize;
import com.javaschool.entitymapper.PackageType;
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
    TypeReference refpackage = new TypeReference<List<PackageType>>() {
    };
    TypeReference refsize = new TypeReference<List<PackageSize>>() {
    };

    public QueueResponseServiceImp(final QueueSenderService queueSenderService, final ObjectMapper mapper) {
        this.queueSenderService = queueSenderService;
        this.mapper = mapper;
    }

    @Override
    public List<PackageType> getType() {
        List<PackageType> types = Collections.emptyList();
        try {
            String response = queueSenderService.sendRequest("packageType");
            types = mapper.readValue(response, refpackage);
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
            sizes = mapper.readValue(response, refsize);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return sizes;
    }
}
