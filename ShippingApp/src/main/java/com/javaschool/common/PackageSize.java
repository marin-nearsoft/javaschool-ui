package com.javaschool.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class PackageSize implements Serializable {

    private int id;
    private String description;
    private BigDecimal priceFactor;

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

    public BigDecimal getPriceFactor() { return priceFactor; }

    public void setPriceFactor(BigDecimal priceFactor) {
        this.priceFactor = priceFactor;
    }
}
