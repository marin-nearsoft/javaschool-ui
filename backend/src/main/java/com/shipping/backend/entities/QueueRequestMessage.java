package com.shipping.backend.entities;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueueRequestMessage implements Serializable {

    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String origin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destination;

    @Override
    public String toString() {
        if ((origin == "") ||
                (origin == null))
            return "{\"type\":\"" + type + "\"}";
        else
            return "{\"type\":\"" + type
                    + "\",\"origin\":\"" +  origin
                    + "\",\"destination\":\"" + destination  +"\"}";
    }
}