package com.javaschool.modelmapper;

import lombok.Data;

@Data
public class Cities {
    private int id;
    private String name;
    private int tax;
    private boolean seaport;
    private boolean airport;
}
