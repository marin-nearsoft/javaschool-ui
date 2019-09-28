package com.javaschool.controller;

import com.javaschool.service.ShippingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class ShippingController {

    private ShippingService shippingService;

    public ShippingController(final ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @RequestMapping("/size")
    public List<String> getSize() {
        return shippingService.getPackageSize();
    }

    @RequestMapping("/type")
    public List<String> getType() {
        return shippingService.getPackageType();
    }

    @RequestMapping("/time")
    public List<String> getTime() {
        return shippingService.getTransportVelocity();
    }

    @RequestMapping("/transport")
    public List<String> getTransport() {
        return shippingService.getTransport();
    }
}
