package com.javaschool.service;

import java.util.List;

public interface BackEndService {

    List<String> getType();

    List<String> getSize();

    List<String> getTransport();

    List<String> getVelocity();

}