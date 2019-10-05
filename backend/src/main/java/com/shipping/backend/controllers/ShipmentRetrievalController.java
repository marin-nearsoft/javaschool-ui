package com.shipping.backend.controllers;


import com.shipping.backend.entities.PackageSize;
import com.shipping.backend.entities.PackageType;
import com.shipping.backend.services.QueueResponseHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shipment-management")
public class ShipmentRetrievalController {

    private QueueResponseHandler queueResponseHandler;

    public ShipmentRetrievalController(final QueueResponseHandler queueResponseHandler){
        this.queueResponseHandler = queueResponseHandler;
    }

    @GetMapping("/package-types")
    public List<String> packageTypes(){
        return queueResponseHandler.getTypes().stream().map(PackageType::getDescription).collect(Collectors.toList());
    }

    @GetMapping("/package-sizes")
    public List<String> packageSizes(){
        return queueResponseHandler.getSizes().stream().map(PackageSize::getDescription).collect(Collectors.toList());
    }

    @GetMapping("/transports")
    public List<String> transports(){
        return queueResponseHandler.getSizes().stream().map(PackageSize::getDescription).collect(Collectors.toList());
    }
}
