package com.javaschool.entitymapper;

import lombok.Data;

@Data
public class Cities {
    private int id;
    private String name;
    private boolean seaport;
    private boolean airport;
}
