package com.javaschool.dijkstra;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node implements Comparable<Node> {
    private String name;
    private List<Edge> adjacenciesList;
    private boolean visited;
    private Node predecessor;
    private double distance = Double.MAX_VALUE;

    public Node(String name) {
        this.name = name;
        this.adjacenciesList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Node otherNode) {
        return Double.compare(this.distance, otherNode.getDistance());
    }

    public void addNeighbour(Edge edge) {
        this.adjacenciesList.add(edge);
    }
}
