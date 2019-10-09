package com.javaschool.modelmapper;

import lombok.Data;

@Data
public class TransportType {
    private int id;
    private String description;
    private double pricePerMile;
}
