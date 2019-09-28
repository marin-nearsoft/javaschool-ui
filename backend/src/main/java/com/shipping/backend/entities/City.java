package com.shipping.backend.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class City implements Serializable {

    private int id;
    private String name;
    private int tax;
    private boolean seaport;
    private boolean airport;

    @Override
    public String toString() {
        return "[{\"id\":" + id
                + ",\"name\":\"" +  name
                +"\",\"tax\":" + tax
                +",\"seaport\":" + seaport
                +",\"airport\":" + airport + "}]";
    }

}
