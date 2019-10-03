package com.shipping.backend.services;


import com.shipping.backend.entities.CityVertex;
import com.shipping.backend.entities.VertexEdge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPathCalculator {

    public void computeShortestPaths(CityVertex sourceVertex){

        sourceVertex.setDistance(0);
        PriorityQueue<CityVertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceVertex);
        sourceVertex.setVisited(true);

        while( !priorityQueue.isEmpty() ){
            // Getting the minimum distance vertex from priority queue
            CityVertex actualVertex = priorityQueue.poll();

            for(VertexEdge edge : actualVertex.getAdjacenciesList()){

                CityVertex v = edge.getTargetVertex();
                if(!v.isVisited())
                {
                    double newDistance = actualVertex.getDistance() + edge.getWeight();

                    if( newDistance < v.getDistance() ){
                        priorityQueue.remove(v);
                        v.setDistance(newDistance);
                        v.setPredecessor(actualVertex);
                        priorityQueue.add(v);
                    }
                }
            }
            actualVertex.setVisited(true);
        }
    }

    public List<CityVertex> getShortestPathTo(CityVertex targetVertex){
        List<CityVertex> path = new ArrayList<>();

        for(CityVertex vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }
}
