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
        return backEndService.getType();
    }

    @RequestMapping("/size")
    public List<String> getSize() {
        return backEndService.getSize();
    }

    @RequestMapping("/transport")
    public List<String> getTransport() {
        return backEndService.getTransport();
    }

    @RequestMapping("/velocity")
    public List<String> getVelocity() {
        return backEndService.getVelocity();
    }

    @RequestMapping("/city")
    public List<String> getCity() {
        return backEndService.getCity();
    }

}
