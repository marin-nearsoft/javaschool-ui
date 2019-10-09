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

    private static final Logger logger = LoggerFactory.getLogger(QueueSender.class);
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
        messageRequest.setType(PACKAGE_SIZE);
        List<PackageSize> packageSizes = queueSender.messageRequest(messageRequest, new PackageSize[0]);
        List<String> sizes = packageSizes.stream()
                                         .map(PackageSize::getDescription)
                                         .collect(Collectors.toList());
        return sizes;
    }

    @Override
    public List<String> getPackageType() {
        messageRequest.setType(PACKAGE_TYPE);
        List<PackageType> packageTypes = queueSender.messageRequest(messageRequest, new PackageType[0]);
        List<String> types = packageTypes.stream()
                                         .map(PackageType::getDescription)
                                         .collect(Collectors.toList());
        return types;
    }

    @Override
    public List<String> getTransportVelocity() {
        messageRequest.setType(TRANSPORT_VELOCITY);
        List<TransportVelocity> transportVelocities = queueSender.messageRequest(messageRequest, new TransportVelocity[0]);
        List<String> velocities = transportVelocities.stream()
                                                     .map(TransportVelocity::getDescription)
                                                     .collect(Collectors.toList());
        return velocities;
    }

    @Override
    public List<String> getTransportType() {
        messageRequest.setType(TRANSPORT_TYPE);
        List<TransportType> transportTypes = queueSender.messageRequest(messageRequest, new TransportType[0]);
        List<String> types = transportTypes.stream()
                                           .map(TransportType::getDescription)
                                           .collect(Collectors.toList());
        return types;
    }

    @Override
    public List<String> getCity() {
        messageRequest.setType(CITY);
        List<City> cities = queueSender.messageRequest(messageRequest, new City[0]);
        List<String> cityList = cities.stream()
                                      .map(City::getName)
                                      .sorted()
                                      .collect(Collectors.toList());
        return cityList;
    }

    @Override
    public List<String> getRoute() {
        List<Route> routes;
        messageRequest.setType(ROUTE);
        messageRequest.setOrigin("Chihuahua");
        messageRequest.setDestination("Cancun");
        routes = queueSender.messageRequest(messageRequest, new Route[0]);
        List<Node> shortestPath = getShortestPath(routes);
        List<String> route = shortestPath.stream()
                                         .map(Node::getName)
                                         .collect(Collectors.toList());
        return route;
    }

    private List<Node> getShortestPath(List<Route> routes){
        List<Node> nodes = new ArrayList<>();
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
        List<Node> path = Dijkstra.setShortestPathTo(destination);
        logger.info("Path: " + path);
        return path;
    }
}
