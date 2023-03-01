package graph;
import java.util.*;

/*
    we use min-heap(priority-queue) to implement the NotProcessedVertexes    
    heap-optimization to decrease the time-cost of finding min-distance vertex from N to log(N):
*/
public class NotProcessedVertexesImpl implements NotProcessedVertexes{
    private PriorityQueue< AbstractMap.SimpleEntry<Vertex,Integer> > vertexes;

    public NotProcessedVertexesImpl(List<Vertex> vertexes){  // initialize the notProcessedVertexes with input collection vertexes
        int len = vertexes.size();
        this.vertexes = new PriorityQueue< AbstractMap.SimpleEntry<Vertex,Integer> >(len, (a,b) -> a.getValue().intValue() - b.getValue().intValue());
        for (Vertex v : vertexes){
            AbstractMap.SimpleEntry<Vertex,Integer> obj = new AbstractMap.SimpleEntry<Vertex,Integer>(v, Integer.valueOf(1<<30));  // we consider 2^30 as +oo
            this.vertexes.add(obj);
        }
    }

    public Vertex popMinVertex(){  // get and remove the vertex with minimal minDistance
        return vertexes.poll().getKey();
    }

    public boolean empty(){  // return true if there is no unprocessed vertex
        return (vertexes.size() == 0);
    }

    public void setMinDistance(Vertex vertex, int old_d, int new_d){  // change the min-distancefrom start vertex to "vertex" as from old_d to new_d
        AbstractMap.SimpleEntry<Vertex,Integer> old_obj = new AbstractMap.SimpleEntry<>(vertex, Integer.valueOf(old_d));
        vertexes.remove(old_obj);
        AbstractMap.SimpleEntry<Vertex,Integer> new_obj = new AbstractMap.SimpleEntry<>(vertex, Integer.valueOf(new_d));
        vertexes.add(new_obj);
    }
    
}