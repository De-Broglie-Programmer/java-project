import maze.*;
import graph.*;
import java.util.*;

public class DijkstraTest{
    public static void main(String[] args){
        Maze mazeA = new Maze(10,10);
        mazeA.initFromTextFile("data/labyrinthe.maze");
        MazeBox startBox = mazeA.getMazeMatrix()[9][0];
        MazeBox endBox = mazeA.getMazeMatrix()[3][7];    
        List<Vertex> shortestPath = Dijkstra.dijkstra(mazeA, startBox, endBox).getShortestPath(startBox, endBox);
        System.out.println(shortestPath.size());
    }
}
