package com.shipping.backend.services;


import com.shipping.backend.entities.CityVertex;
import com.shipping.backend.entities.Route;
import com.shipping.backend.entities.VertexEdge;

import java.util.*;

public class RoutesResponseProcessor {

    public HashMap<String, CityVertex> getRoutesMap(List<Route> routeList){

        HashMap<String, CityVertex> cityVertexMap = new HashMap<String, CityVertex>();

        for(Route route: routeList){
            if(cityVertexMap.containsKey(route.getFrom())==false){

                CityVertex fromVertex = new CityVertex(route.getFrom());
                cityVertexMap.put(route.getFrom(), fromVertex);

                System.out.println("New vertex added: " + cityVertexMap.get(route.getFrom()).getName());

                if(cityVertexMap.containsKey(route.getTo())==false){
                    CityVertex toVertex = new CityVertex(route.getTo());
                    cityVertexMap.put(route.getTo(), toVertex);

                    System.out.println("New vertex added: " + cityVertexMap.get(route.getTo()).getName());

                    VertexEdge edge = new VertexEdge(route.getDistance(), fromVertex, toVertex);
                    fromVertex.addNeighbour(edge);

                    System.out.println("New vertex edged added");

                }else{
                    VertexEdge edge = new VertexEdge( route.getDistance(), fromVertex, cityVertexMap.get(route.getTo()));
                    fromVertex.addNeighbour(edge);
                }

            }else{

                if(cityVertexMap.containsKey(route.getTo())==false){
                    CityVertex toVertex = new CityVertex(route.getTo());
                    cityVertexMap.put(route.getTo(), toVertex);

                    System.out.println("New vertex added to existing from: " + cityVertexMap.get(route.getTo()).getName());

                    VertexEdge edge = new VertexEdge(route.getDistance(), cityVertexMap.get(route.getFrom()), toVertex);
                    cityVertexMap.get(route.getFrom()).addNeighbour(edge);

                    System.out.println("New vertex edge added");

                }else{
                    VertexEdge edge = new VertexEdge( route.getDistance(), cityVertexMap.get(route.getFrom()), cityVertexMap.get(route.getTo()));
                    cityVertexMap.get(route.getFrom()).addNeighbour(edge);

                    System.out.println("New vertex edge added to existing from");
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

    public List<CityVertex> getShortestPathTo(CityVertex targetVertex){
        List<CityVertex> path = new ArrayList<>();

        for(CityVertex vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }
}
