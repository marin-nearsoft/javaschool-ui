package com.nearsoft.project.resources.source;

import org.springframework.stereotype.Service;

@Service
public class Sizes {

    private int id;
    private String description;
    private double priceFactor;

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPriceFactor() {
        return priceFactor;
    }
}
