package com.shipping.backend.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class PriceRequest {

    private String size;
    private String type;
    private String time;
    private String transport;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;

}
