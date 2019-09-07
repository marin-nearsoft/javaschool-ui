package com.javaschool.model;


import java.util.List;

public interface ShippingService {

    List<String> getSize();

    List<String> getType();

    List<String> getTime();

    List<String> getTransport();
}
