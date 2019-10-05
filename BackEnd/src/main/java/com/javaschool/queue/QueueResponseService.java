package com.javaschool.queue;

import com.javaschool.dijkstra.RouteList;
import com.javaschool.modelmapper.*;

import java.util.List;

public interface QueueResponseService {

    List<PackageType> getType();

    List<PackageSize> getSize();

    List<TransportType> getTransport();

    List<TransportVelocity> getVelocity();

    List<Cities> getCity();

    List<RouteList> getRoute(String origin, String destination);
}
