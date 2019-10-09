package com.shipping.backend.models.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Route implements Serializable {

    private String from;
    private String to;
    private double distance;

}
