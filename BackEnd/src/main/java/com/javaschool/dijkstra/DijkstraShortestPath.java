package com.javaschool.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath {
    public void computeShortestPaths(Node origin){
        origin.setDistance(0);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(origin);
        origin.setVisited(true);


        while( !priorityQueue.isEmpty() ){
            // Getting the minimum distance vertex from priority queue
            Node actualVertex = priorityQueue.poll();

            for(Edge edge : actualVertex.getAdjacenciesList()){

                Node v = edge.getDestiny();
                if(!v.isVisited())
                {
                    double newDistance = actualVertex.getDistance() + edge.getDistance();

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

    public List<Node> getShortestPathTo(Node targetVertex){
        List<Node> path = new ArrayList<>();

        for(Node vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }

}
