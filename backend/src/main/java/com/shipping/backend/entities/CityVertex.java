package com.shipping.backend.entities;

import java.util.ArrayList;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VertexEdge> getAdjacenciesList() {
        return adjacenciesList;
    }

    public void setAdjacenciesList(List<VertexEdge> adjacenciesList) {
        this.adjacenciesList = adjacenciesList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public CityVertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(CityVertex predecessor) {
        this.predecessor = predecessor;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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
