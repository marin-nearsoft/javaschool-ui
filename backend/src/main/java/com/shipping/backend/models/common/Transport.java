package com.shipping.backend.models.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transport implements Serializable {

    private int id;
    private String description;
    private int pricePerMile;

    @Override
    public String toString() {
        return "[{\"id\":" + id
                + ",\"description\":\"" + description
                + "\",\"pricePerMile\":" + pricePerMile + "}]";
    }

}
