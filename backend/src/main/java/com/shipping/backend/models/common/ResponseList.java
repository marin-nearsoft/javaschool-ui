package com.shipping.backend.models.common;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ResponseList {

    List<PackageType> packageTypes;

    List<PackageSize> packageSizes;

    List<Transport> transports;

    List<TransportVelocity> transportVelocities;

    List<City> cities;

    List<Route> routes;

    public String RoutesToString(List<Route> routeList) {
        String routesString = "[";
        for (int route = 0; route < routeList.size(); route++) {
            routesString += "{\"from\":\"" + routeList.get(route).getFrom()
                    + "\",\"to\":\"" + routeList.get(route).getTo()
                    + "\",\"distance\":\"" + routeList.get(route).getDistance() + "\"}";
            if ((routeList.size() - route) != 1)
                routesString += ",";
        }
        return routesString + "]";
    }

}
