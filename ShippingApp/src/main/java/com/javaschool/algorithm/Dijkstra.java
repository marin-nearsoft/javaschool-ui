package com.javaschool.algorithm;

import com.javaschool.common.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    public static void computePaths(Node source) {
        source.setDistance(0);
        PriorityQueue<Node> nodesQueue = new PriorityQueue<>();
        nodesQueue.add(source);

        while (!nodesQueue.isEmpty()) {
            Node removedNode = nodesQueue.poll();

            for (Map.Entry<Node, Integer> entry : removedNode.getAdjacentNodes().entrySet()) {
                Node node = entry.getKey();
                int distance = entry.getValue();
                int distanceRemoveNode = removedNode.getDistance() + distance;

                if (distanceRemoveNode < node.getDistance()) {
                    nodesQueue.remove(node);
                    node.setDistance(distanceRemoveNode);
                    node.setPreviousNode(removedNode);
                    nodesQueue.add(node);
                }
            }
        }
    }

    public static List<Node> setShortestPathTo(Node target) {
        List<Node> path = new ArrayList();
        Map<List<Node>, Integer> shortestPath = new HashMap<>();
        for (Node node = target; node != null; node = node.getPreviousNode()) {
            path.add(node);
        }
        Collections.reverse(path);
        shortestPath.put(path,target.getDistance());
        target.setShortestPath(shortestPath);
        return path;
    }

    public static List<Node> getShortestPath(List<Route> routes, String origin, String destination){
        List<Node> nodes = new ArrayList<>();
        for (Route route : routes) {
            Node originNode = new Node(route.getFrom());
            Node destinationNode = new Node(route.getTo());

            if (nodes.contains(originNode)) {
                originNode = nodes.get(nodes.indexOf(originNode));
            }
            if (nodes.contains(destinationNode)) {
                destinationNode = nodes.get(nodes.indexOf(destinationNode));
            }

            originNode.addDestination(destinationNode, route.getDistance());

            if (!nodes.contains(originNode)) {
                nodes.add(originNode);
            }
            if (!nodes.contains(destinationNode)) {
                nodes.add(destinationNode);
            }
        }
        computePaths(nodes.get(nodes.indexOf(new Node(origin))));
        return setShortestPathTo(nodes.get(nodes.indexOf(new Node(destination))));
    }
}