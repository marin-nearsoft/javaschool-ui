package com.javaschool.dijkstra;

import lombok.Data;

@Data
public class Edge {
    private double distance;
    private Node source;
    private Node destiny;

    public Edge(Node source, Node destiny,double distance) {
        this.source = source;
        this.destiny = destiny;
        this.distance = distance;
    }
}
