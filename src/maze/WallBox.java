package maze;

public class WallBox extends MazeBox{
    
    public WallBox(int line_no, int col_no, Maze maze){
        super(line_no, col_no, maze);
    }

    public boolean isPass(){
        return false;
    }

    public String getType(){
        return "Wall";
    }
}