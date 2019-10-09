package com.javaschool.controller;

import com.javaschool.service.ShippingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShippingController {

    private ShippingService shippingService;

    public ShippingController(final ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @RequestMapping("/size")
    public List<String> getSize() { return shippingService.getPackageSize(); }

    @RequestMapping("/type")
    public List<String> getType() {
        return shippingService.getPackageType();
    }

    @RequestMapping("/time")
    public List<String> getTime() {
        return shippingService.getTransportVelocity();
    }

    @RequestMapping("/transport")
    public List<String> getTransport() { return shippingService.getTransportType(); }

    @RequestMapping("/city")
    public List<String> getCity() {
        return shippingService.getCity();
    }

    @RequestMapping("/cityPath")
    public List<String> getCityPath() {
        return shippingService.getCity();
    }

    @RequestMapping("/checkPrice")
    public List<String> getRoute(){
        return shippingService.getRoute();
    }
}
