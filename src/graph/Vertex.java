package graph;
import java.util.*;

public interface Vertex{
    public String getLabel();
    public List<Vertex> getNeighbors();
    public boolean isPass();
    public int getLine();
    public int getColone();
}
