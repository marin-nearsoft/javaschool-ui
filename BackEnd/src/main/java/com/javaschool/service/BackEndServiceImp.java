package com.javaschool.service;

import com.javaschool.dijkstra.DijkstraShortestPath;
import com.javaschool.dijkstra.Edge;
import com.javaschool.dijkstra.Node;
import com.javaschool.entitymapper.*;
import com.javaschool.queue.QueueResponseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BackEndServiceImp implements BackEndService {

    private QueueResponseService queueResponseService;

    public BackEndServiceImp(final QueueResponseService queueResponseService) {
        this.queueResponseService = queueResponseService;
    }

    @Override
    public List<String> getType() {
        return queueResponseService.getType().stream()
                .map(PackageType::getDescription).collect(Collectors.toList());
    }

    @Override
    public List<String> getSize() {
        return queueResponseService.getSize().stream()
                .map(PackageSize::getDescription).collect(Collectors.toList());
    }

    @Override
    public List<String> getTransport() {
        return queueResponseService.getTransport().stream()
                .map(TransportType::getDescription).collect(Collectors.toList());
    }

    @Override
    public List<String> getVelocity() {
        return queueResponseService.getVelocity().stream()
                .map(TransportVelocity::getDescription).collect(Collectors.toList());
    }

    @Override
    public List<String> getCity() {
        return queueResponseService.getCity().stream()
                .map(Cities::getName).sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> getRoute(String origin, String destination) {

        List<RouteList> routeresponse = queueResponseService.getRoute(origin, destination);
        HashMap<String, Node> listOfNodes = new HashMap<String, Node>();

        for (RouteList node : routeresponse) {
            double Distance = node.getDistance();

            if (listOfNodes.containsKey(node.getFrom())) {
                Node nodefrom = listOfNodes.get(node.getFrom());
                if (listOfNodes.containsKey(node.getTo())) {
                    Node nodeto = listOfNodes.get(node.getTo());

                    Edge edge = new Edge(nodefrom, nodeto, Distance);
                    nodefrom.addNeighbour(edge);

                    listOfNodes.replace(node.getFrom(), nodefrom);
                } else {
                    Node newnodeto = new Node(node.getTo());
                    listOfNodes.put(node.getTo(), newnodeto);

                    Edge edge = new Edge(nodefrom, newnodeto, Distance);
                    nodefrom.addNeighbour(edge);

                    listOfNodes.replace(node.getFrom(), nodefrom);
                }
            } else {
                Node newnodefrom = new Node(node.getFrom());
                listOfNodes.put(node.getFrom(), newnodefrom);

                if (listOfNodes.containsKey(node.getTo())) {
                    Node nodeto = listOfNodes.get(node.getTo());

                    Edge edge = new Edge(newnodefrom, nodeto, Distance);
                    newnodefrom.addNeighbour(edge);

                    listOfNodes.replace(node.getFrom(), newnodefrom);
                } else {
                    Node newnodeto = new Node(node.getTo());
                    listOfNodes.put(node.getTo(), newnodeto);

                    Edge edge = new Edge(newnodefrom, newnodeto, Distance);
                    newnodefrom.addNeighbour(edge);
                }
            }
        }
        DijkstraShortestPath shortestPath = new DijkstraShortestPath();
        shortestPath.computeShortestPaths(listOfNodes.get(origin));

        List<String> routes = new ArrayList<>();
        for (Node node : shortestPath.getShortestPathTo(listOfNodes.get(destination))) {
            routes.add(String.valueOf(node));
        }
        return routes;
    }
}
