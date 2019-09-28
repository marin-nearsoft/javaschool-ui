package com.nearsoft.javaschoolbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private int id;

    private String name;

    private int tax;

    private boolean seaport;

    private boolean airport;

}
