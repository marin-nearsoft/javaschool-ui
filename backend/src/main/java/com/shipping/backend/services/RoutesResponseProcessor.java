package com.shipping.backend.services;


import com.shipping.backend.models.dijkstra.CityVertex;
import com.shipping.backend.models.common.Route;
import com.shipping.backend.models.dijkstra.VertexEdge;

import java.util.*;

public class RoutesResponseProcessor {

    public HashMap<String, CityVertex> getRoutesMap(List<Route> routeList){

        HashMap<String, CityVertex> cityVertexMap = new HashMap<String, CityVertex>();

        for(Route route : routeList){
            if(!cityVertexMap.containsKey(route.getFrom())){

                CityVertex fromVertex = new CityVertex(route.getFrom());
                cityVertexMap.put(route.getFrom(), fromVertex);

                if(!cityVertexMap.containsKey(route.getTo())){
                    CityVertex toVertex = new CityVertex(route.getTo());
                    cityVertexMap.put(route.getTo(), toVertex);
                    VertexEdge edge = new VertexEdge(route.getDistance(), fromVertex, toVertex);
                    fromVertex.addNeighbour(edge);
                }else{
                    VertexEdge edge = new VertexEdge( route.getDistance(), fromVertex, cityVertexMap.get(route.getTo()));
                    fromVertex.addNeighbour(edge);
                }
            }else{
                if(!cityVertexMap.containsKey(route.getTo())){
                    CityVertex toVertex = new CityVertex(route.getTo());
                    cityVertexMap.put(route.getTo(), toVertex);
                    VertexEdge edge = new VertexEdge(route.getDistance(), cityVertexMap.get(route.getFrom()), toVertex);
                    cityVertexMap.get(route.getFrom()).addNeighbour(edge);
                }else{
                    VertexEdge edge = new VertexEdge( route.getDistance(), cityVertexMap.get(route.getFrom()), cityVertexMap.get(route.getTo()));
                    cityVertexMap.get(route.getFrom()).addNeighbour(edge);
                }
            }
        }

        return cityVertexMap;

    }

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

    public List<String> getShortestPathTo(CityVertex targetVertex){
        List<String> path = new ArrayList<>();

        for(CityVertex vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
            path.add(vertex.getName());
        }

        Collections.reverse(path);
        return path;
    }
}
