package com.javaschool.common;

import java.io.Serializable;

public class QueueMessageRequest implements Serializable {

    private String type;

    public String getType() { return type; }

    public void setType(String type) {
        this.type = type;
    }
}
