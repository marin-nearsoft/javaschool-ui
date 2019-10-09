package com.javaschool.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Node implements Comparable<Node> {
    private String name;

    private Map<List<Node>, Integer> shortestPath = new HashMap<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    private Node previousNode;

    public void addDestination(Node destination, int distance) {
        getAdjacentNodes().put(destination, distance);
    }

    public Node(String name) {
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<List<Node>, Integer> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(Map<List<Node>, Integer> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public Node getPreviousNode() {
        return this.previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(distance, other.distance);
    }
}

