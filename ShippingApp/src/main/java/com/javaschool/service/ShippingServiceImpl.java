package com.javaschool.service;

import com.javaschool.algorithm.Dijkstra;
import com.javaschool.algorithm.Node;
import com.javaschool.common.*;
import com.javaschool.queue.QueueSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingServiceImpl implements ShippingService {

    private QueueSender queueSender;
    private QueueMessageRequest messageRequest;
    private ShippingCharacteristics shippingCharacteristics = new ShippingCharacteristics();
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
        messageRequest = new QueueMessageRequest();
        messageRequest.setType(PACKAGE_SIZE);
        List<PackageSize> packageSizes = queueSender.messageRequest(messageRequest, new PackageSize[0]);
        shippingCharacteristics.setPackageSize(packageSizes);
        List<String> sizes = packageSizes.stream()
                .map(PackageSize::getDescription)
                .collect(Collectors.toList());
        return sizes;
    }

    @Override
    public List<String> getPackageType() {
        messageRequest = new QueueMessageRequest();
        messageRequest.setType(PACKAGE_TYPE);
        List<PackageType> packageTypes = queueSender.messageRequest(messageRequest, new PackageType[0]);
        shippingCharacteristics.setPackageType(packageTypes);
        List<String> types = packageTypes.stream()
                .map(PackageType::getDescription)
                .collect(Collectors.toList());
        return types;
    }

    @Override
    public List<String> getTransportVelocity() {
        messageRequest = new QueueMessageRequest();
        messageRequest.setType(TRANSPORT_VELOCITY);
        List<TransportVelocity> transportVelocities = queueSender.messageRequest(messageRequest, new TransportVelocity[0]);
        shippingCharacteristics.setTransportVelocity(transportVelocities);
        List<String> velocities = transportVelocities.stream()
                .map(TransportVelocity::getDescription)
                .collect(Collectors.toList());
        return velocities;
    }

    @Override
    public List<String> getTransportType() {
        messageRequest = new QueueMessageRequest();
        messageRequest.setType(TRANSPORT_TYPE);
        List<TransportType> transportTypes = queueSender.messageRequest(messageRequest, new TransportType[0]);
        shippingCharacteristics.setTransportType(transportTypes);
        List<String> types = transportTypes.stream()
                .map(TransportType::getDescription)
                .collect(Collectors.toList());
        return types;
    }

    @Override
    public List<String> getCity() {
        messageRequest = new QueueMessageRequest();
        messageRequest.setType(CITY);
        List<City> cities = queueSender.messageRequest(messageRequest, new City[0]);
        List<String> cityList = cities.stream()
                .map(City::getName)
                .sorted()
                .collect(Collectors.toList());
        return cityList;
    }

    @Override
    public ShippingInformation getShippingInformation(ShippingPayload shippingPayload) {
        ShippingInformation shippingInformation = new ShippingInformation();
        shippingInformation.setPath(getPath(shippingPayload.getOrigin(), shippingPayload.getDestination()));
        shippingInformation.setPrice(getPrice(shippingPayload));
        return shippingInformation;
    }

    private BigDecimal getPrice(ShippingPayload shippingPayload) {
        PackageType packageType = shippingCharacteristics.getPackageType().stream()
                .filter(type -> shippingPayload.getType().equals(type.getDescription()))
                .findAny()
                .orElse(null);

        PackageSize packageSize = shippingCharacteristics.getPackageSize().stream()
                .filter(size -> shippingPayload.getSize().equals(size.getDescription()))
                .findAny()
                .orElse(null);

        TransportType transportType = shippingCharacteristics.getTransportType().stream()
                .filter(transport -> shippingPayload.getTransport().equals(transport.getDescription()))
                .findAny()
                .orElse(null);

        TransportVelocity transportVelocity = shippingCharacteristics.getTransportVelocity().stream()
                .filter(time -> shippingPayload.getTime().equals(time.getDescription()))
                .findAny()
                .orElse(null);

        BigDecimal pricePackageType = packageType.getPrice();
        BigDecimal pricePackageSize = packageSize.getPriceFactor().divide(new BigDecimal(100));
        BigDecimal priceTransportType = transportType.getPricePerMile();
        BigDecimal priceTransportVelocity = transportVelocity.getPriceFactor().divide(new BigDecimal(100));

        return pricePackageType.multiply(pricePackageSize)
                .add(priceTransportType.multiply(priceTransportVelocity));
    }

    private List<String> getPath(String origin, String destination) {
        List<Route> routes;
        messageRequest = new QueueMessageRequest();
        messageRequest.setType(ROUTE);
        messageRequest.setOrigin(origin);
        messageRequest.setDestination(destination);
        routes = queueSender.messageRequest(messageRequest, new Route[0]);
        List<Node> shortestPath = Dijkstra.getShortestPath(routes, messageRequest.getOrigin(), messageRequest.getDestination());
        List<String> path = shortestPath.stream()
                .map(Node::getName)
                .collect(Collectors.toList());
        return path;
    }
}
