package com.javaschool.controller;

import com.javaschool.model.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

    @RequestMapping("/size")
    public List getSize() {
        return shippingService.getSize();
    }

    @RequestMapping("/type")
    public List getType() {
        return shippingService.getType();
    }

    @RequestMapping("/time")
    public List getTime() {
        return shippingService.getTime();
    }

    @RequestMapping("/transport")
    public List getTransport() { return shippingService.getTransport(); }
}
