package com.javaschool.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class City implements Serializable {

    private int id;
    private String name;
    private int tax;
    private boolean seaport;
    private boolean airport;
}
