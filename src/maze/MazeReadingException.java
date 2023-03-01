package maze;

/*
    throws MazeReadingException if the initFile does not match the size of mazeMatrix
    MazeReadingException records the name of initFile and lineno of error
*/
class MazeReadingException extends Exception{
    public final String fileName;
    public final int lineNo;

    public MazeReadingException(String fileName, int lineNo){
        super("MazeReadingException: " + fileName + ", line: " + lineNo + ", matrix size not matches");
        this.fileName = fileName;
        this.lineNo = lineNo;
    }

    public String getName(){
        return fileName;
    }

    public int getLineNo(){
        return lineNo;
    }
    
}