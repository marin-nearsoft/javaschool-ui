package com.javaschool.queue;


import com.javaschool.entitymapper.*;

import java.util.List;

public interface QueueResponseService {

    List<PackageType> getType();

    List<PackageSize> getSize();

    List<TransportType> getTransport();

    List<TransportVelocity> getVelocity();

    List<Cities> getCity();
}
