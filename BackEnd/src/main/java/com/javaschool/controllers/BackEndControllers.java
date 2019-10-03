package com.javaschool.controllers;

import com.javaschool.entitymapper.Route;
import com.javaschool.service.BackEndService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BackEndControllers {

    private BackEndService backEndService;

    public BackEndControllers(final BackEndService backEndService) {
        this.backEndService = backEndService;
    }

    @GetMapping("/type")
    public List<String> getType() {
        List<String> types = backEndService.getType();
        return types;
    }

    @GetMapping("/size")
    public List<String> getSize() {
        List<String> sizes = backEndService.getSize();
        return sizes;
    }

    @GetMapping("/transport")
    public List<String> getTransport() {
        List<String> transports = backEndService.getTransport();
        return transports;
    }

    @GetMapping("/velocity")
    public List<String> getVelocity() {
        List<String> velocities = backEndService.getVelocity();
        return velocities;
    }

    @GetMapping("/city")
    public List<String> getCity() {
        List<String> cities = backEndService.getCity();
        return cities;
    }

    @PostMapping("/route")
    public List<String> getRoute(@RequestBody Route values) {
        String origin = values.getOrigin();
        String destination = values.getDestination();

        List<String> routepath = backEndService.getRoute(origin, destination);
        return routepath;
    }

}
