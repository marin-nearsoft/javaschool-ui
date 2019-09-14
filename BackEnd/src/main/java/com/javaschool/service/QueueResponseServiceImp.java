package com.javaschool.service;

import com.javaschool.entitymapper.PackageType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class QueueResponseServiceImp implements QueueResponseService{

    private static final Logger logger = LoggerFactory.getLogger(QueueSenderServiceImp.class);

    private QueueSenderService queueSenderService;
    private ObjectMapper mapper;

    public QueueResponseServiceImp(final QueueSenderService queueSenderService, final ObjectMapper mapper) {
        this.queueSenderService=queueSenderService;
        this.mapper=mapper;
    }

    @Override
    public List<PackageType> getType() {
        List<PackageType> types = null;

        try{
            String response = queueSenderService.SendRequest("packageType");
            types = mapper.readValue(response, new TypeReference<List<PackageType>>(){});
        } catch(IOException e){
            logger.error(e.getMessage());
        }

        return types;
    }
}
