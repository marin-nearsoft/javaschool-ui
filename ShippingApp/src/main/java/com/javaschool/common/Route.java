package com.javaschool.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Route implements Serializable {

    private String from;
    private String to;
    private int distance;

}
