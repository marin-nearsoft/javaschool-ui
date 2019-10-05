package com.nearsoft.project.rest;

import com.nearsoft.project.rabbitmq.implementation.RabbitmqImpl;
import com.nearsoft.project.resources.implementation.ShippingLists;
import com.nearsoft.project.resources.implementation.ShippingListsImpl;
import com.nearsoft.project.resources.rabbit.SourceMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class Rest {

    private ShippingLists lists;

    public Rest(ShippingListsImpl shippingListsImpl){
        this.lists = shippingListsImpl;
    }

    @Autowired
    RabbitmqImpl rabbitmqImpl;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = GET, value = "/sizes")
    public List<String> sizes() throws IOException {
        String jsonList = rabbitmqImpl.getMessage(SourceMsg.SIZES.source);
        return lists.getSizes(jsonList);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(method = GET, value = "/types")
    public List<String> types()  throws IOException{
        String jsonList = rabbitmqImpl.getMessage(SourceMsg.TYPES.source);
        return lists.getTypes(jsonList);
    }

}
