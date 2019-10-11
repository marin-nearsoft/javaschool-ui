package com.javaschool.common;

import java.util.List;

public class ShippingCharacteristics {

    private List<PackageType> packageType;
    private List<PackageSize> packageSize;
    private List<TransportType> transportType;
    private List<TransportVelocity> transportVelocity;

    public List<PackageType> getPackageType() {
        return packageType;
    }

    public void setPackageType(List<PackageType> packageType) {
        this.packageType = packageType;
    }

    public List<PackageSize> getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(List<PackageSize> packageSize) {
        this.packageSize = packageSize;
    }

    public List<TransportType> getTransportType() {
        return transportType;
    }

    public void setTransportType(List<TransportType> transportType) {
        this.transportType = transportType;
    }

    public List<TransportVelocity> getTransportVelocity() {
        return transportVelocity;
    }

    public void setTransportVelocity(List<TransportVelocity> transportVelocity) {
        this.transportVelocity = transportVelocity;
    }
}
