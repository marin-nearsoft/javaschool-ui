package com.javaschool.entitymapper;

import lombok.Data;

@Data
public class TransportType {
    private int id;
    private String description;
    private int pricePerMile;
}
