package com.javaschool.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TransportType implements Serializable {

    private int id;
    private String description;
    private int pricePerMile;
}
