package graph;

public interface NotProcessedVertexes{
    public boolean empty();  // return true if there is no unprocessed vertex
    public Vertex popMinVertex();  // get and remove the vertex with minimal minDistance
    public void setMinDistance(Vertex vertex, int old_d, int new_d);  // change the min-distancefrom start vertex to "vertex" as from old_d to new_d
}