package graph;

public interface MinDistance{
    public Integer getMinDistance(Vertex vertex);  // get the distance of shortest path from start vertex to "vertex"
    public void setMinDistance(Vertex vertex, int d);  // set the distance of shortest path from start vertex to "vertex" as d
}