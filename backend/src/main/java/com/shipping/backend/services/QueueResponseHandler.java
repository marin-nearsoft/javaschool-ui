package com.shipping.backend.services;

import com.shipping.backend.entities.*;

import java.util.List;

public interface QueueResponseHandler {
    List<PackageType> getTypes();

    List<PackageSize> getSizes();

    List<Transport> getTransports();

    List<TransportVelocity> getTransportVelocity();

    List<City> getCities();

    List<CityVertex> getRoutes();
}
