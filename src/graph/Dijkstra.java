package graph;

import java.util.*;

/* 
    method dijkstra takes in a non-oriented graph, a pair of start-end vertex ,
    and returns an object "shortestPath" including the shortest path from start vertex to end vertex
    complexity O(N^2)
*/                                                                                                                                                                       
public class Dijkstra{
    public static ShortestPath dijkstra(Graph graph, Vertex startVertex, Vertex endVertex){
        // initialisation
        ShortestPath shortestPath = new ShortestPathImpl(graph.getAllVertexes());
        MinDistance minDistance = new MinDistanceImpl(graph.getAllVertexes());
        NotProcessedVertexes notProcessedVertexes = new NotProcessedVertexesImpl(graph.getAllVertexes());
        minDistance.setMinDistance(startVertex, 0);
        notProcessedVertexes.setMinDistance(startVertex, 1<<30, 0);
        Vertex pivot;  
        while(!notProcessedVertexes.empty()){
            pivot = notProcessedVertexes.popMinVertex();
            if (pivot == endVertex)   // stop when finding the shortest path to endVertex
                break;
            int pivotDistance = minDistance.getMinDistance(pivot);
            List<Vertex> successors = graph.getSuccessors(pivot);
            for (Vertex vertex : successors){
                int oldDistance = minDistance.getMinDistance(vertex);
                int newDistance = pivotDistance + graph.getDistance(pivot,vertex);
                if (oldDistance > newDistance){
                    minDistance.setMinDistance(vertex, pivotDistance + graph.getDistance(pivot,vertex));
                    notProcessedVertexes.setMinDistance(vertex, oldDistance, newDistance);
                    shortestPath.setPreviousVertex(vertex, pivot);
                }
            }
        }
        return shortestPath;
    }
}