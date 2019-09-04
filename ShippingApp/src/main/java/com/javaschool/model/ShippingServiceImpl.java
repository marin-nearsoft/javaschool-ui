package com.javaschool.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {
    @Override
    public List getSize() {
        ArrayList<String> sizes = new ArrayList<>();
        sizes.add("Small");
        sizes.add("Medium");
        sizes.add("Large");
        return sizes;
    }

    @Override
    public List getType() {
        ArrayList<String> types = new ArrayList<>();
        types.add("Envelope");
        types.add("Box");
        return types;
    }

    @Override
    public List getTime() {
        ArrayList<String> times = new ArrayList<>();
        times.add("Express");
        times.add("Regular");
        times.add("Slow");
        return times;
    }

    @Override
    public List getTransport() {
        ArrayList<String> transports = new ArrayList<>();
        transports.add("Land");
        transports.add("Air");
        return transports;
    }
}
