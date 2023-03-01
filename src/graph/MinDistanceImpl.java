package graph;
import java.util.*;

/*
    we use HashMap to implement the MinDistance
    the KV structure help us get/modify the min-distance with complexity O(1)
*/
public class MinDistanceImpl implements MinDistance{
    private HashMap<Vertex, Integer> minDistanceRecorder;

    public MinDistanceImpl(List<Vertex> vertexes){
        int len = vertexes.size();
        minDistanceRecorder = new HashMap<Vertex, Integer>(len);
        for (Vertex v : vertexes){
            minDistanceRecorder.put(v,Integer.valueOf(1<<30));
        }
    }

    public Integer getMinDistance(Vertex vertex){  // get the distance of shortest path from start vertex to "vertex"
        Integer minD = Integer.valueOf(minDistanceRecorder.get(vertex));
        return minD;
    }
    
    public void setMinDistance(Vertex vertex, int d){  // set the distance of shortest path from start vertex to "vertex" as d 
        Integer D = Integer.valueOf(d);
        minDistanceRecorder.put(vertex,D);
    }
}