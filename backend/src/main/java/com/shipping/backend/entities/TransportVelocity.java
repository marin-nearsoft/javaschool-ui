package com.shipping.backend.entities;


import lombok.Data;

import java.io.Serializable;

@Data
public class TransportVelocity implements Serializable {

    private int id;
    private String description;
    private int priceFactor;

    @Override
    public String toString() {
        return "[{\"id\":" + id
                + ",\"description\":\"" + description
                + "\",\"priceFactor\":" + priceFactor + "}]";
    }

}
