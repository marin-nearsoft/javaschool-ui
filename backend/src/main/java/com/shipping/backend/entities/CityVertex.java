package com.shipping.backend.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityVertex implements Comparable<CityVertex>{

    private String name;
    private List<VertexEdge> adjacenciesList;
    private boolean visited;
    private CityVertex predecessor;
    private double distance = Double.MAX_VALUE;

    public CityVertex(String name) {
        this.name = name;
        this.adjacenciesList = new ArrayList<>();
    }

    public void addNeighbour(VertexEdge edge) {
        this.adjacenciesList.add(edge);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(CityVertex otherVertex) {
        return Double.compare(this.distance, otherVertex.getDistance());
    }
}
