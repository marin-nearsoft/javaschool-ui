package com.javaschool.modelmapper;

import com.javaschool.dijkstra.RouteList;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ResponseList {

    List<PackageType> listType;

    List<PackageSize> listSize;

    List<TransportType> listTransport;

    List<TransportVelocity> listVelocity;

    List<Cities> listCity;

    List<RouteList> routeresponse;
}
