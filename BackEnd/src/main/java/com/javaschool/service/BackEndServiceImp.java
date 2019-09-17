package com.javaschool.service;

import com.javaschool.entitymapper.PackageType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BackEndServiceImp implements BackEndService {

    private QueueResponseService queueResponseService;

    public BackEndServiceImp(final QueueResponseService queueResponseService) {
        this.queueResponseService=queueResponseService;
    }

    @Override
    public List<String> getType() {
    return queueResponseService.getType().stream()
            .map(PackageType::getDescription).collect(Collectors.toList());
    }
}
