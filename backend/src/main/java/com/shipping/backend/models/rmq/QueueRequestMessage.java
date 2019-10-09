package com.shipping.backend.models.rmq;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueueRequestMessage implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
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