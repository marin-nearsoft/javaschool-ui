package com.shipping.backend.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueueRouteRequestMessage implements Serializable {

    private String type;
    private String origin;
    private String destination;

    @Override
    public String toString() {
        return "{\"type\":\"" + type
                + "\",\"origin\":\"" +  origin
                + "\",\"destination\":\"" + destination +"\"}";
    }

}
