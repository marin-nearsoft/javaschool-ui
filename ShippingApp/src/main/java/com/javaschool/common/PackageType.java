package com.javaschool.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PackageType implements Serializable {

    private int id;
    private String description;
    private int price;
}
