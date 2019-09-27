package com.javaschool.service;

import com.javaschool.entitymapper.PackageSize;
import com.javaschool.entitymapper.PackageType;
import com.javaschool.queue.QueueResponseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BackEndServiceImp implements BackEndService {

    private QueueResponseService queueResponseService;

    public BackEndServiceImp(final QueueResponseService queueResponseService) {
        this.queueResponseService = queueResponseService;
    }

    @Override
    public List<String> getType() {
        return queueResponseService.getType().stream()
                .map(PackageType::getDescription).collect(Collectors.toList());
    }

    @Override
    public List<String> getSize() {
        return queueResponseService.getSize().stream()
                .map(PackageSize::getDescription).collect(Collectors.toList());
    }


}
