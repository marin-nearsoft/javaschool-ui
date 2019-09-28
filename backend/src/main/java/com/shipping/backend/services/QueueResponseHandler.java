package com.shipping.backend.services;

import com.shipping.backend.entities.PackageSize;
import com.shipping.backend.entities.PackageType;
import com.shipping.backend.entities.Transport;

import java.util.List;

public interface QueueResponseHandler {
    public List<PackageType> getTypes();
    public List<PackageSize> getSizes();
    public List<Transport> getTransports();
}
