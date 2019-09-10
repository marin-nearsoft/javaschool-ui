package com.JavaSchool.Service;

import org.springframework.stereotype.Service;

@Service
public class BackEndServiceImp implements BackEndService {

    @Override
    public String[] getCities() {
        //String[] Cities= {"Chihuahua", "Monterrey", "Mexico"};
        return new String[]{"Chihuahua", "Monterrey", "Mexico"};
    }

}
