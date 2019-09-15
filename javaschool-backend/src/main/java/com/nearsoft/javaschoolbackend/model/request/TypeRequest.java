package com.nearsoft.javaschoolbackend.model.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TypeRequest {

    private String type;

    public TypeRequest(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toJSONString() throws JsonProcessingException {
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(this);
    }
}
