package com.javaschool.common;

import java.io.Serializable;

public class City implements Serializable {

    private int id;
    private String name;
    private int tax;
    private boolean seaport;
    private boolean airport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public boolean isSeaport() {
        return seaport;
    }

    public void setSeaport(boolean seaport) {
        this.seaport = seaport;
    }

    public boolean isAirport() {
        return airport;
    }

    public void setAirport(boolean airport) {
        this.airport = airport;
    }
}
