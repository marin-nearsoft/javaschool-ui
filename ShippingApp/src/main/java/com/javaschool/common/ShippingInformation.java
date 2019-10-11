package com.javaschool.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShippingInformation implements Serializable {
    private String folio;
    private List<String> path;
    private BigDecimal price;

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
