package com.javaschool.service;

import com.javaschool.modelmapper.Information;

import java.util.List;

public interface BackEndService {

    List<String> getType();

    List<String> getSize();

    List<String> getTransport();

    List<String> getVelocity();

    List<String> getCity();

    List<String> getRoute(String origin, String destination);

    double getPrice(String size, String type, String time, String transport);

    List<Information> getInformation();

    Information postInformation(String size, String type, String time, String transport, double price, String path);

}
