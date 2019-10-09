package com.javaschool.common;

import java.io.Serializable;

public class QueueMessageRequest implements Serializable {

    private String type;
    private String origin;
    private String destination;

    public String getType() { return type; }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
