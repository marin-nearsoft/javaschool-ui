package com.javaschool.service;

import com.javaschool.algorithm.Dijkstra;
import com.javaschool.algorithm.Node;
import com.javaschool.common.*;
import com.javaschool.queue.QueueSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingServiceImpl implements ShippingService {

    private static final Logger logger = LoggerFactory.getLogger(ShippingServiceImpl[].class);
    private QueueSender queueSender;
    private QueueMessageRequest messageRequest = new QueueMessageRequest();
    private static final String PACKAGE_SIZE = "packageSize";
    private static final String PACKAGE_TYPE = "packageType";
    private static final String TRANSPORT_VELOCITY = "transportVelocity";
    private static final String TRANSPORT_TYPE = "transportType";
    private static final String CITY = "city";
    private static final String ROUTE = "routesList";

    public ShippingServiceImpl(final QueueSender queueSender) {
        this.queueSender = queueSender;
    }

    @Override
    public List<String> getPackageSize() {
        List<PackageSize> packageSizes;
        List<String> sizes;
        messageRequest.setType(PACKAGE_SIZE);
        try {
            packageSizes = queueSender.messageRequest(messageRequest, new PackageSize[0]);
            sizes = packageSizes.stream()
                    .map(size -> size.getDescription())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return sizes;
    }

    @Override
    public List<String> getPackageType() {
        List<PackageType> packageTypes;
        List<String> types;
        messageRequest.setType(PACKAGE_TYPE);
        try {
            packageTypes = queueSender.messageRequest(messageRequest, new PackageType[0]);
            types = packageTypes.stream()
                    .map(type -> type.getDescription())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return types;
    }

    @Override
    public List<String> getTransportVelocity() {
        List<TransportVelocity> transportVelocities;
        List<String> velocities;
        messageRequest.setType(TRANSPORT_VELOCITY);
        try {
            transportVelocities = queueSender.messageRequest(messageRequest, new TransportVelocity[0]);
            velocities = transportVelocities.stream()
                    .map(velocity -> velocity.getDescription())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return velocities;
    }

    @Override
    public List<String> getTransportType() {
        List<TransportType> transportTypes;
        List<String> types;
        messageRequest.setType(TRANSPORT_TYPE);
        try {
            transportTypes = queueSender.messageRequest(messageRequest, new TransportType[0]);
            types = transportTypes.stream()
                    .map(type -> type.getDescription())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return types;
    }

    @Override
    public List<String> getCity(){
        List<City> cities;
        List<String> cityList;
        messageRequest.setType(CITY);
        try {
            cities = queueSender.messageRequest(messageRequest, new City[0]);
            cityList = cities.stream()
                    .map(type -> type.getName())
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return cityList;
    }

    @Override
    public List<Route> getRoute() {
        List<Route> routes;
        List<Node> nodes = new ArrayList<>();
        messageRequest.setType(ROUTE);
        messageRequest.setOrigin("Chihuahua");
        messageRequest.setDestination("Cancun");
        try {
            routes = queueSender.messageRequest(messageRequest, new Route[0]);
            for (Route route : routes) {
                Node origin = new Node(route.getFrom());
                Node destiny = new Node(route.getTo());

                if (nodes.contains(origin)) {
                    origin = nodes.get(nodes.indexOf(origin));
                }
                if (nodes.contains(destiny)) {
                    destiny = nodes.get(nodes.indexOf(destiny));
                }

                origin.addDestination(destiny, route.getDistance());

                if (!nodes.contains(origin)) {
                    nodes.add(origin);
                }
                if (!nodes.contains(destiny)) {
                    nodes.add(destiny);
                }
            }

            Node origin = nodes.get(nodes.indexOf(new Node(messageRequest.getOrigin())));
            Node destination = nodes.get(nodes.indexOf(new Node(messageRequest.getDestination())));

            Dijkstra.computePaths(origin);
            Dijkstra.setShortestPathTo(destination);

            logger.info("Stop");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new QueueException(e.getMessage());
        }
        return routes;
    }
}
