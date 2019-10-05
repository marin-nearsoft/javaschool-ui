package com.nearsoft.project.resources.source;

import com.nearsoft.project.resources.implementation.SourcesImpl;

public class Cities implements SourcesImpl {

        private int id;
        private String name;
        private int tax;
        private Boolean seaport;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public void setSeaport(Boolean seaport) {
        this.seaport = seaport;
    }

    public void setAirport(Boolean airport) {
        this.airport = airport;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTax() {
        return tax;
    }

    public Boolean getSeaport() {
        return seaport;
    }

    public Boolean getAirport() {
        return airport;
    }

    private Boolean airport;

    }
