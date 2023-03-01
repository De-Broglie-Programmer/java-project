package graph;
import java.util.*;

/*
    we use HashMap to implement the ShortestPath
    the KV structure help us get/modify the pre-vertex with complexity O(1)
*/

public class ShortestPathImpl implements ShortestPath{
    private HashMap<Vertex,Vertex> paths;

    public ShortestPathImpl(List<Vertex> vertexes){
        int len = vertexes.size();
        paths = new HashMap<Vertex,Vertex>(len);
    }
    
    public Vertex getPreviousVertex(Vertex vertex){
        return paths.get(vertex);
    }

    public void setPreviousVertex(Vertex now, Vertex pre){
        paths.put(now,pre);
    }

    public List<Vertex> getShortestPath(Vertex startVertex, Vertex endVertex){  /* get an array of vertexes included in the shortest path 
                                                                from end vertex to start vertex */
        List<Vertex> shortestPath = new ArrayList<Vertex>();
        Vertex now = endVertex;
        shortestPath.add(endVertex);
        while (now != startVertex){
            Vertex pre = paths.get(now);
            shortestPath.add(pre);
            now = pre;
        }
        return shortestPath;
    }
}