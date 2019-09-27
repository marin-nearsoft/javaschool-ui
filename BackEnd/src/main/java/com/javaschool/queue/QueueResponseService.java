package com.javaschool.queue;

import com.javaschool.entitymapper.PackageSize;
import com.javaschool.entitymapper.PackageType;

import java.util.List;

public interface QueueResponseService {

    List<PackageType> getType();

    List<PackageSize> getSize();
}
