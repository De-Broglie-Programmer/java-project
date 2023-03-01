package maze;

public class EmptyBox extends MazeBox{

    public EmptyBox(int line_no, int col_no, Maze maze){
        super(line_no, col_no, maze);
    }

    public boolean isPass(){
        return true;
    }
    
    public String getType(){
        return "Empty";
    }
}