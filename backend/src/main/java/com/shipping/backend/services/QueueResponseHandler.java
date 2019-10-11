package com.shipping.backend.services;

import com.shipping.backend.models.common.*;
import com.shipping.backend.models.dijkstra.CityVertex;

import java.util.List;

public interface QueueResponseHandler {
    List<PackageType> getTypes();

    List<PackageSize> getSizes();

    List<Transport> getTransports();

    List<TransportVelocity> getTransportVelocity();

    List<City> getCities();

    List<String> getRoutes(String origin, String destination);

    double getPrice(String size, String type, String time, String transport);

    List<ShipmentInformation> getInformation();

}
