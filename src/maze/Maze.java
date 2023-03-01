package maze;

import graph.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Maze implements Graph{
    private int lineNum, colNum;  // size of maze
    private MazeBox[][] mazeMatrix;

    public Maze(int lineNum, int colNum){
        this.lineNum = lineNum;
        this.colNum = colNum;
        mazeMatrix = new MazeBox[lineNum][colNum];
    }

    public List<Vertex> getAllVertexes(){  // maybe there is a bug
        List<Vertex> allVertexes = new ArrayList<Vertex>(lineNum*colNum);
        for (int i=0; i<lineNum; i++){
            for (int j=0; j<colNum; j++){
                allVertexes.add(mazeMatrix[i][j]);
            }
        }
        return allVertexes;
    }

    public int getDistance(Vertex src,Vertex dst){
        return 1;
    }

    public List<Vertex> getNeighbors(Vertex mazebox){
        int line = mazebox.getLine(); 
        int col = mazebox.getColone();  
        List<Vertex> neighbors =  new ArrayList<Vertex>(6); // {left, upleft, upright, right, downleft, downright} 
        int upleft = (line%2 == 0) ? col-1 : col;
        int downleft = (line%2 == 0) ? col-1 : col;
        int upright = (line%2 == 0) ? col : col+1;
        int downright = (line%2 == 0) ? col : col+1;
        if (col != 0)
            neighbors.add(mazeMatrix[line][col-1]);  // left-neighbor
        if (col != colNum-1)
            neighbors.add(mazeMatrix[line][col+1]);  // right-neighbor
        if (line != 0 && (col != 0 || line%2 == 1))
            neighbors.add(mazeMatrix[line-1][upleft]);  // leftup-neighbor
        if (line != 0 && (col != colNum-1 || line%2 == 0))   
            neighbors.add(mazeMatrix[line-1][upright]);  // rightup-neighbor
        if (line != lineNum-1 && (col != 0 || line%2 == 1))
            neighbors.add(mazeMatrix[line+1][downleft]);  // leftdown-neighbor
        if (line != lineNum-1 && (col != colNum-1 || line%2 == 0))   
            neighbors.add(mazeMatrix[line+1][downright]);  // rightdown-neighbor
        return neighbors;
    }

    // get the accessible neighbors (not a wall)
    public List<Vertex> getSuccessors(Vertex mazebox){
        List<Vertex> neighbors = getNeighbors(mazebox);
        List<Vertex> successors =  new ArrayList<Vertex>(6);
        for (Vertex box : neighbors){
            if (box.isPass())  
                successors.add(box);
        }

        return successors;
    }
  
    public final void initFromTextFile(String fileName){ 
        try {
            Path pathToFile = Paths.get(fileName);
            System.out.println(pathToFile.toAbsolutePath());
            BufferedReader in = Files.newBufferedReader(Paths.get(fileName));
            String str = null;
            int i = 0;
            while ((str = in.readLine()) != null){
                if (str.length() != colNum || i >= lineNum)  // length of line does not match or too many lines in inputfile
                    throw new MazeReadingException(fileName, i);
                for (int j=0;j<str.length();j++){
                    char box=str.charAt(j);
                    if (box == 'W'){
                        mazeMatrix[i][j] = new WallBox(i,j,this);
                        //System.out.println("ok");
                    }
                    else if(box == 'A')
                        mazeMatrix[i][j] = new ArrivalBox(i,j,this);
                    else if (box == 'D')
                        mazeMatrix[i][j] = new DepartureBox(i,j,this);
                    else if(box == 'E')
                        mazeMatrix[i][j] = new EmptyBox(i,j,this);
                }
                System.out.println(str);
                i++;
            }
            if (i < lineNum)
                throw new MazeReadingException(fileName, i);
        } 
        catch(MazeReadingException  ex){
            System.out.println( ex.getMessage() + ex);
        }
        catch(NoSuchFileException ex){
            System.out.println("cannot find file: " + fileName + ", " + ex);
        }
        catch(InvalidPathException ex){
            System.out.println("the path string cannot be converted to a Path: " + ex);
        }
        catch (IOException ex){
            System.out.println("##an I/O error occurs: " + ex);
        }
    }

    public final void saveToTextFile(String fileName){
        try {
            Path path = Paths.get(fileName);  
            String lineStr = new String();
            for (int i=0; i<lineNum; i++){
                for (int j=0; j<colNum; j++){
                    if ((mazeMatrix[i][j]).getType() == "Empty")
                        lineStr = lineStr + "E";
                    if (mazeMatrix[i][j].getType() == "Wall")
                        lineStr = lineStr + "W";
                    if (mazeMatrix[i][j].getType() == "Departure")
                        lineStr = lineStr + "D";
                    if (mazeMatrix[i][j].getType() == "Arrival")
                        lineStr = lineStr + "A";
                }
                lineStr = lineStr + System.lineSeparator();
            }
            Files.write(path, lineStr.getBytes());
        }
        catch(SecurityException ex){
            System.out.println("write access denied: " + fileName + ", " + ex);
            File file = new File(fileName);
            file.setWritable(false);
        }     
        catch(InvalidPathException ex){
            System.out.println("the path string cannot be converted to a Path: " + ex);
        }
        catch (IOException ex){
            System.out.println("an I/O error occurs while Files.write: " + ex);
        }
    }

    public MazeBox[][] getMazeMatrix(){
        return mazeMatrix;
    }
}
