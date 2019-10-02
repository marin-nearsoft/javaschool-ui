package com.shipping.backend.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Route implements Serializable {

    private String from;
    private String to;
    private String distance;

    public String RoutesToString(List<Route> routeList){
        String routesString = "[";
        for (int route=0; route < routeList.size(); route++) {
            routesString +=  "{\"from\":\"" + routeList.get(route).getFrom()
                    + "\",\"to\":\"" +  routeList.get(route).getTo()
                    +"\",\"distance\":\"" + routeList.get(route).getDistance() + "\"}";
            if((routeList.size()-route) != 1)
                routesString += ",";


        }
        return routesString + "]";
    }

}
