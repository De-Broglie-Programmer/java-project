package maze;

import graph.*;
import java.util.*;

public abstract class MazeBox implements Vertex{
    private int line;
    private int col;
    private Maze maze;
    public abstract boolean isPass();
    public abstract String getType();

    public MazeBox(int line_no, int col_no, Maze maze){
        line = line_no;
        col = col_no;
        this.maze = maze;
    } 

    public final int getLine(){
        return line;
    }

    public final int getColone(){
        return col;
    }

    public String getLabel(){
        return "(" + line + "," + col + ")";
    }

    public List<Vertex> getNeighbors(){
        return maze.getNeighbors(this);
    }

    public List<Vertex> getSuccessors(){  // get the accessible neighbors (not a wall)
        return maze.getSuccessors(this);
    }
    
}