package com.shipping.backend.models.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class PackageType implements Serializable {

    private int id;
    private String description;
    private int price;

    @Override
    public String toString() {
        return "[{\"id\":" + id
                + ",\"description\":\"" +  description
                +"\",\"price\":" + price + "}]";
    }

}
