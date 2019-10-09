package com.javaschool.modelmapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Price {
    String size;
    String type;
    String time;
    String transport;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    double price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String path;
}
