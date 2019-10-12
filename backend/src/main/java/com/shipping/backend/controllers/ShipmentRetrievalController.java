package com.shipping.backend.controllers;


import com.shipping.backend.models.common.*;
import com.shipping.backend.models.request.PriceRequest;
import com.shipping.backend.models.request.RouteRequest;
import com.shipping.backend.services.QueueResponseHandler;
import org.springframework.web.bind.annotation.*;

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
        return queueResponseHandler.getTransports().stream().map(Transport::getDescription).collect(Collectors.toList());
    }

    @GetMapping("/transports/velocities")
    public List<String> transportVelocities(){
        return queueResponseHandler.getTransportVelocity().stream().map(TransportVelocity::getDescription).collect(Collectors.toList());
    }

    @GetMapping("/cities")
    public List<String> cities(){
        return queueResponseHandler.getCities().stream().map(City::getName).collect(Collectors.toList());
    }

    @PostMapping("/route")
    public List<String> getRoute(@RequestBody RouteRequest values) {
        return queueResponseHandler.getRoutes(values.getOrigin(), values.getDestination());
    }

    @PostMapping("/price")
    public double getPrice(@RequestBody PriceRequest values) {

        return queueResponseHandler.getPrice(values.getSize(), values.getType(), values.getTime(), values.getTransport());
    }

    @GetMapping("/information")
    public List<ShipmentInformation> getInformation() {
        return queueResponseHandler.getInformation();
    }

    @PostMapping("/confirmation")
    public ShipmentInformation postInformation(@RequestBody PriceRequest values) {

        return queueResponseHandler.postInformation(values.getPrice(), values.getPath());

    }

}
