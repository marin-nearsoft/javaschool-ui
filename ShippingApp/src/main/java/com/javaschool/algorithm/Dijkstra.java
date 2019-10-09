package com.javaschool.algorithm;

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
}