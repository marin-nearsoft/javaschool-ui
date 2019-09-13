package com.JavaSchool.Controllers;


import com.JavaSchool.Service.BackEndService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BackEndControllers {

    private BackEndService backEndService;
    
    public BackEndControllers(final BackEndService backEndService) {
        this.backEndService = backEndService;
    }

    @RequestMapping("/Type")
    public List<String> getType() {
        return backEndService.getType();
    }

}
