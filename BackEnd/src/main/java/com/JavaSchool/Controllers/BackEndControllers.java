package com.JavaSchool.Controllers;


import com.JavaSchool.EntityMapper.PackageType;
import com.JavaSchool.Service.BackEndService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BackEndControllers {

    private BackEndService backEndService;
    private ObjectMapper mapper;
    
    public BackEndControllers(final BackEndService backEndService) {
        this.backEndService = backEndService;
    }

    @RequestMapping("/Type")
    public List<String> getType() {
        List<PackageType> types = backEndService.getType();
        List<String> listoftypes = new ArrayList<>();

        for (PackageType type : types) {
            listoftypes.add(type.getDescription());
        }

        return listoftypes;
    }

}
