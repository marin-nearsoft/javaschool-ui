package com.javaschool.queue;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSenderServiceImp.class);

    private QueueSenderService queueSenderService;
    private ObjectMapper mapper;
    TypeReference ref = new TypeReference<List<PackageType>>(){};

    public QueueResponseServiceImp(final QueueSenderService queueSenderService, final ObjectMapper mapper) {
        this.queueSenderService=queueSenderService;
        this.mapper=mapper;
    }

    @Override
    public List<PackageType> getType() {
        List<PackageType> types = null;
        try{
            String response = queueSenderService.sendRequest("packageType");
            types = mapper.readValue(response, ref);
        } catch(IOException e){
            LOGGER.error(e.getMessage(), e);
        }

        return types;

    }
}
