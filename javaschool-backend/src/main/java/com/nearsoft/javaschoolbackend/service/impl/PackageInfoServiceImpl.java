package com.nearsoft.javaschoolbackend.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nearsoft.javaschoolbackend.model.response.PackageSize;
import com.nearsoft.javaschoolbackend.model.response.PackageType;
import com.nearsoft.javaschoolbackend.service.PackageInfoService;
import com.nearsoft.javaschoolbackend.util.RabbitMQSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackageInfoServiceImpl implements PackageInfoService {

    private final RabbitMQSender rabbitMQSender;

    private final ObjectMapper objectMapper;

    public PackageInfoServiceImpl(RabbitMQSender rabbitMQSender, ObjectMapper objectMapper){
        this.rabbitMQSender = rabbitMQSender;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<String> getPackageTypes() {
        List<PackageType> packageTypes = new ArrayList<PackageType>();
        try {
            packageTypes = objectMapper.readValue(rabbitMQSender.send("packageType"), new TypeReference<List<PackageType>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> res = new ArrayList<String>();
        for(PackageType type: packageTypes){
            res.add(type.getDescription());
        }
        return res;
    }

    @Override
    public List<String> getPackageSizes() {
        List<PackageSize> packageSizes = new ArrayList<PackageSize>();
        try {
            packageSizes = objectMapper.readValue(rabbitMQSender.send("packageSize"), new TypeReference<List<PackageSize>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> res = new ArrayList<String>();
        for(PackageSize size: packageSizes){
            res.add(size.getDescription());
        }
        return res;
    }
}
