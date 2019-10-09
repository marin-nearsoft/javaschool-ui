package com.javaschool.service;

import com.javaschool.dijkstra.DijkstraShortestPath;
import com.javaschool.dijkstra.Edge;
import com.javaschool.dijkstra.Node;
import com.javaschool.dijkstra.RouteList;
import com.javaschool.modelmapper.*;
import com.javaschool.queue.QueueResponseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BackEndServiceImp implements BackEndService {

    private QueueResponseService queueResponseService;
    private ResponseList responseList;


    BackEndServiceImp(final QueueResponseService queueResponseService, final ResponseList responseList) {
        this.queueResponseService = queueResponseService;
        this.responseList = responseList;
    }

    @Override
    public List<String> getType() {
        responseList.setListType(queueResponseService.getType());
        return responseList.getListType().stream().map(PackageType::getDescription).sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getSize() {
        responseList.setListSize(queueResponseService.getSize());
        return responseList.getListSize().stream()
                .map(PackageSize::getDescription).sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getTransport() {
        responseList.setListTransport(queueResponseService.getTransport());
        return responseList.getListTransport().stream()
                .map(TransportType::getDescription).sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getVelocity() {
        responseList.setListVelocity(queueResponseService.getVelocity());
        return responseList.getListVelocity().stream()
                .map(TransportVelocity::getDescription).sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getCity() {
        responseList.setListCity(queueResponseService.getCity());
        return responseList.getListCity().stream()
                .map(Cities::getName).sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getRoute(String origin, String destination) {
        responseList.setRouteresponse(queueResponseService.getRoute(origin, destination));
        HashMap<String, Node> listOfNodes = new HashMap<>();

        for (RouteList node : responseList.getRouteresponse()) {

            if (listOfNodes.containsKey(node.getFrom())) {
                if (listOfNodes.containsKey(node.getTo())) {
                    Edge edge = new Edge(listOfNodes.get(node.getFrom()), listOfNodes.get(node.getTo()), node.getDistance());
                    listOfNodes.get(node.getFrom()).addNeighbour(edge);

                } else {

                    Node newnodeto = new Node(node.getTo());
                    listOfNodes.put(node.getTo(), newnodeto);

                    Edge edge = new Edge(listOfNodes.get(node.getFrom()), newnodeto, node.getDistance());
                    listOfNodes.get(node.getFrom()).addNeighbour(edge);
                }
            } else {

                Node newnodefrom = new Node(node.getFrom());
                listOfNodes.put(node.getFrom(), newnodefrom);

                if (listOfNodes.containsKey(node.getTo())) {

                    Edge edge = new Edge(newnodefrom, listOfNodes.get(node.getTo()), node.getDistance());
                    newnodefrom.addNeighbour(edge);

                } else {
                    Node newnodeto = new Node(node.getTo());
                    listOfNodes.put(node.getTo(), newnodeto);

                    Edge edge = new Edge(newnodefrom, newnodeto, node.getDistance());
                    newnodefrom.addNeighbour(edge);
                }
            }
        }
        DijkstraShortestPath shortestPath = new DijkstraShortestPath();
        shortestPath.computeShortestPaths(listOfNodes.get(origin));

        System.out.println("To " + listOfNodes.get(destination) + ":" + listOfNodes.get(destination).getDistance());
        System.out.println("Path To " + listOfNodes.get(destination) + ":" + shortestPath.getShortestPathTo(listOfNodes.get(destination)));

        List<String> routes = new ArrayList<>();
        for (Node node : shortestPath.getShortestPathTo(listOfNodes.get(destination))) {
            routes.add(String.valueOf(node));
        }

        return routes;
    }

    @Override
    public double getPrice(String size, String type, String time, String transport) {

        double typeprice = responseList.getListType().stream().filter(currentype -> type.equals(currentype.getDescription()))
                .mapToDouble(PackageType::getPrice).sum();

        double sizeprice = responseList.getListSize().stream().filter(currensize -> size.equals(currensize.getDescription()))
                .mapToDouble(PackageSize::getPriceFactor).sum();

        double transportprice = responseList.getListTransport().stream().filter(currenttransport -> transport.equals(currenttransport.getDescription()))
                .mapToDouble(TransportType::getPricePerMile).sum();

        double velocityprice = responseList.getListVelocity().stream().filter(currentvelocity -> time.equals(currentvelocity.getDescription()))
                .mapToDouble(TransportVelocity::getPriceFactor).sum();

        return (typeprice * (sizeprice / 100)) + (transportprice * (velocityprice / 100));
    }

    @Override
    public List<Information> getInformation() {
        responseList.setInformation(queueResponseService.getInformation());
        return queueResponseService.getInformation();
    }

    @Override
    public Information postInformation(String size, String type, String time, String transport, double price, String path) {

        return queueResponseService.postInformation(price, path);
    }
}
