package com.nearsoft.javaschoolbackend.model.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeRequest {

    private String type;

    public String toJSONString() throws JsonProcessingException {
        ObjectMapper ob = new ObjectMapper();
        return ob.writeValueAsString(this);
    }
}
