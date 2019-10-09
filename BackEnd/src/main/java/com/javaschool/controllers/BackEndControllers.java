package com.javaschool.controllers;

import com.javaschool.modelmapper.Information;
import com.javaschool.modelmapper.Price;
import com.javaschool.modelmapper.Route;
import com.javaschool.service.BackEndService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class BackEndControllers {

    private BackEndService backEndService;

    public BackEndControllers(final BackEndService backEndService) {
        this.backEndService = backEndService;
    }

    @GetMapping("/type")
    public List<String> getType() {
        return backEndService.getType();
    }

    @GetMapping("/size")
    public List<String> getSize() {
        return backEndService.getSize();
    }

    @GetMapping("/transport")
    public List<String> getTransport() {
        return backEndService.getTransport();
    }

    @GetMapping("/velocity")
    public List<String> getVelocity() {
        return backEndService.getVelocity();
    }

    @GetMapping("/city")
    public List<String> getCity() {
        return backEndService.getCity();
    }

    @PostMapping("/route")
    public List<String> getRoute(@RequestBody Route values) {

        return backEndService.getRoute(values.getOrigin(), values.getDestination());
    }

    @PostMapping("/price")
    public double getPrice(@RequestBody Price values) {

        return backEndService.getPrice(values.getSize(), values.getType(), values.getTime(), values.getTransport());
    }

    @GetMapping("/information")
    public List<Information> getInformation() {
        return backEndService.getInformation();
    }

    @PostMapping("/send")
    public Information postInformation(@RequestBody Price values) {

        return backEndService.postInformation(values.getSize(), values.getType(),
                values.getTime(), values.getTransport(), values.getPrice(), values.getPath());

    }

}
