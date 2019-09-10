package com.JavaSchool.Controllers;


import com.JavaSchool.Service.BackEndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackEndControllers {

    @Autowired
    BackEndService backEndService;

    //@RequestMapping(method = RequestMethod.GET, value = "/Cities")
    @RequestMapping("/Cities")
    public String[] Cities() {
        return backEndService.getCities();
    }


}
