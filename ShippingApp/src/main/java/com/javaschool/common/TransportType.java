package com.javaschool.common;

import java.io.Serializable;

public class TransportType implements Serializable {

    private int id;
    private String description;
    private int pricePerMile;

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

    public int getPricePerMile() {
        return pricePerMile;
    }

    public void setPricePerMile(int pricePerMile) {
        this.pricePerMile = pricePerMile;
    }
}
