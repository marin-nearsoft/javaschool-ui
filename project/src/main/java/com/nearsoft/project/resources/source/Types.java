package com.nearsoft.project.resources.source;

import com.nearsoft.project.resources.implementation.SourcesImpl;
import org.springframework.stereotype.Service;

@Service
public class Types implements SourcesImpl {
    private int id;
    private String description;
    private double price;

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
