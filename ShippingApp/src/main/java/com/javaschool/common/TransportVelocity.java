package com.javaschool.common;

import java.io.Serializable;

public class TransportVelocity implements Serializable {

    private int id;
    private String description;
    private int priceFactor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(int priceFactor) {
        this.priceFactor = priceFactor;
    }
}
