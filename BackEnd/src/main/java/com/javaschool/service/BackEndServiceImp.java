package com.javaschool.service;

import com.javaschool.entitymapper.PackageType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackEndServiceImp implements BackEndService {

    private QueueResponseService queueResponseService;

    public BackEndServiceImp(final QueueResponseService queueResponseService) {
        this.queueResponseService=queueResponseService;
    }

    @Override
    public List<String> getType() {
        List<PackageType> typesResponse = queueResponseService.getType();

        List<String> listOfTypes = new ArrayList<>();
        for (PackageType type : typesResponse) {
            listOfTypes.add(type.getDescription());
        }

        return listOfTypes;
    }
}
