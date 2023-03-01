package graph;
import java.util.*;

public interface ShortestPath{
    public List<Vertex> getShortestPath(Vertex startVertex, Vertex endVertex);  /* get an array of vertexes included in the shortest path 
                                                                from end vertex to start vertex */
    public Vertex getPreviousVertex(Vertex vertex);
    public void setPreviousVertex(Vertex now, Vertex pre);
}
