package com.nearsoft.javaschoolbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MockController {

    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public List<Integer> getSizeList(){
        List<Integer> result = new ArrayList<Integer>();
        result.add(10);
        result.add(20);
        result.add(30);
        return result;
    }

}
