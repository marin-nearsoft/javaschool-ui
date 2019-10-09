package com.shipping.backend.models.dijkstra;

import lombok.Data;

@Data
public class VertexEdge {

    private double weight;
    private CityVertex startVertex;
    private CityVertex targetVertex;

    public VertexEdge(double weight, CityVertex startVertex, CityVertex targetVertex) {
        this.weight = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

}
