package com.shipping.backend.models.common;

import lombok.Data;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Data
public class ShipmentInformation implements Serializable {

    private int folio;
    private String path;
    private double price;

    public String getPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        return format.format(price);
    }

}
