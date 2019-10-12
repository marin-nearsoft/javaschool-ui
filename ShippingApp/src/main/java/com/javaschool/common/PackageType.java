package com.javaschool.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class PackageType implements Serializable {

    private int id;
    private String description;
    private BigDecimal price;

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

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
