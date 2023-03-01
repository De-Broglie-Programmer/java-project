package maze;

public class ArrivalBox extends MazeBox{

    public ArrivalBox(int line_no, int col_no, Maze maze){
        super(line_no, col_no, maze);
    }

    public boolean isPass(){
        return true;
    }
    
    public String getType(){
        return "Arrival";
    }
}