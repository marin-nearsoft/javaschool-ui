package com.nearsoft.javaschoolbackend.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nearsoft.javaschoolbackend.exception.custom.PackageDataException;
import com.nearsoft.javaschoolbackend.model.response.PackageSize;
import com.nearsoft.javaschoolbackend.model.response.PackageType;
import com.nearsoft.javaschoolbackend.service.PackageInfoService;
import com.nearsoft.javaschoolbackend.util.RabbitMQSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PackageInfoServiceImpl implements PackageInfoService {

    private final RabbitMQSender rabbitMQSender;

    private final ObjectMapper objectMapper;

    @Override
    public List<String> getPackageTypes() {
        List<PackageType> packageTypes = new ArrayList<PackageType>();
        List<String> res;

        try {
            packageTypes = objectMapper.readValue(rabbitMQSender.send("packageType"), new TypeReference<List<PackageType>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new PackageDataException("JSON mapping error occurred");
        }

        res = packageTypes.stream().map(type -> type.getDescription()).collect(Collectors.toList());
        return res;
    }

    @Override
    public List<String> getPackageSizes() {
        List<PackageSize> packageSizes = new ArrayList<PackageSize>();
        List<String> res;
        try {
            packageSizes = objectMapper.readValue(rabbitMQSender.send("packageSize"), new TypeReference<List<PackageSize>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        res = packageSizes.stream().map(size -> size.getDescription()).collect(Collectors.toList());
        return res;
    }
}
