package com.shipping.backend.models.request;

import lombok.Data;

@Data
public class PriceRequest {

    String size;
    String type;
    String time;
    String transport;

}
