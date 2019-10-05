package com.shipping.backend.services;

import com.shipping.backend.entities.PackageSize;
import com.shipping.backend.entities.PackageType;
import com.shipping.backend.entities.Transport;

import java.util.List;

public interface QueueResponseHandler {
    List<PackageType> getTypes();

    List<PackageSize> getSizes();

    List<Transport> getTransports();
}
