package com.shipping.backend.entities;

public class VertexEdge {

    private double weight;
    private CityVertex startVertex;
    private CityVertex targetVertex;

    public VertexEdge(double weight, CityVertex startVertex, CityVertex targetVertex) {
        this.weight = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public CityVertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(CityVertex startVertex) {
        this.startVertex = startVertex;
    }

    public CityVertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(CityVertex targetVertex) {
        this.targetVertex = targetVertex;
    }
}
