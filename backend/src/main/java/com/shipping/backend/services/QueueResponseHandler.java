package com.shipping.backend.services;

import com.shipping.backend.entities.*;

import java.util.List;

public interface QueueResponseHandler {
    public List<PackageType> getTypes();
    public List<PackageSize> getSizes();
    public List<Transport> getTransports();
    public List<TransportVelocity> getTransportVelocity();
    public List<City> getCities();
}
