package com.shipping.backend.controllers;


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
        return queueResponseHandler.getTypes().stream().map(packageType -> packageType.getDescription()).collect(Collectors.toList());
    }

    @GetMapping("/package-sizes")
    public List<String> packageSizes(){
        return queueResponseHandler.getSizes().stream().map(packageSize -> packageSize.getDescription()).collect(Collectors.toList());
    }

    @GetMapping("/transports")
    public List<String> transports(){
        return queueResponseHandler.getSizes().stream().map(transports -> transports.getDescription()).collect(Collectors.toList());
    }

    @GetMapping("/transports/velocities")
    public List<String> transportVelocities(){
        return queueResponseHandler.getSizes().stream().map(transportVelocities -> transportVelocities.getDescription()).collect(Collectors.toList());
    }

    @GetMapping("/cities")
    public List<String> cities(){
        return queueResponseHandler.getCities().stream().map(cities -> cities.getName()).collect(Collectors.toList());
    }

}
