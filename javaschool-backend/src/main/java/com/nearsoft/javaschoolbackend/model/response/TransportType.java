package com.nearsoft.javaschoolbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportType {

    private int id;

    private String description;

    private int pricePerMile;

}
