package com.shipping.backend.models.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class PackageSize implements Serializable {

    private int id;
    private String description;
    private int priceFactor;

    @Override
    public String toString() {
        return "[{\"id\":" + id
                + ",\"description\":\"" +  description
                +"\",\"priceFactor\":" + priceFactor + "}]";
    }

}
