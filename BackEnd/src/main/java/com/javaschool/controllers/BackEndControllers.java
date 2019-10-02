package com.javaschool.controllers;

import com.javaschool.service.BackEndService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BackEndControllers {

    private BackEndService backEndService;

    public BackEndControllers(final BackEndService backEndService) {
        this.backEndService = backEndService;
    }

    @RequestMapping("/type")
    public List<String> getType() {
        List<String> types = backEndService.getType();
        return types;
    }

    @RequestMapping("/size")
    public List<String> getSize() {
        List<String> sizes = backEndService.getSize();
        return sizes;
    }

    @RequestMapping("/transport")
    public List<String> getTransport() {
        List<String> transports = backEndService.getTransport();
        return transports;
    }

    @RequestMapping("/velocity")
    public List<String> getVelocity() {
        List<String> velocities = backEndService.getVelocity();
        return velocities;
    }

}
