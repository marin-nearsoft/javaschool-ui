package com.javaschool.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransportType implements Serializable {

    private int id;
    private String description;
    private BigDecimal pricePerMile;

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

    public BigDecimal getPricePerMile() { return pricePerMile; }

    public void setPricePerMile(BigDecimal pricePerMile) {
        this.pricePerMile = pricePerMile;
    }
}
