import maze.*;

public class MazeTest{
    public static void main(String[] args){
        Maze mazeA = new Maze(10,10);
        mazeA.initFromTextFile("data/labyrinthe.maze");
        mazeA.saveToTextFile("data/labyrinthe4.maze");
    }
}
