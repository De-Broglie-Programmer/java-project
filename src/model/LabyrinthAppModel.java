package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.*;
import java.nio.file.*;

import graph.*;
import maze.*;

public class LabyrinthAppModel {
    private List<ChangeListener> listeners = new LinkedList<ChangeListener>();

	private Maze maze;
	private MazeBox startBox, endBox;
	private int currentBoxLineNo, currentBoxColNo;

	private Hexagon currentHexagon = null;
	private Color currentColor = new Color(236, 246, 238);  // empty box

	// editedHexagons represents a 2-dimensional array, get(i) returns A[i/colNum][i%colNum]
	private List<Hexagon> editedHexagons = new ArrayList<Hexagon>(1024);
	private List<Hexagon> pathHexagons = new ArrayList<Hexagon>(128);
	private int labyrinthWidth = 0;
	private int labyrinthHeight = 0;
	private int hexagonWidth = 10;
	private int hexagonHeight = 10;
	private int lineNum = 0;
	private int colNum = 0;

	private static final Color emptyColor = new Color(236, 246, 238);  // grey
	private static final Color wallColor = new Color(17, 30, 92);  // black
	private static final Color startColor = new Color(0, 128, 255);  // blue
	private static final Color destinationColor = new Color(255, 0, 0);  // red
	private static final Color pathColor = new Color(0, 255, 255);  // blue+green

	private boolean modified = false;

	private boolean hasStart = false;
	private boolean hasDestination = false;
	private boolean isComplete = false;
	private boolean hasPath = false;

	public void stateChanges() {
		ChangeEvent evt = new ChangeEvent(this);
		
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}

    public void addObserver(ChangeListener listener) {
		listeners.add(listener);
	}

	public void loadFromFile(String absPath){
		loadMaze(absPath);
		loadLabyrinth(absPath);
		hasDestination = true;
		hasStart = true;
		isComplete = true;

	}

	public void loadMaze(String absPath){
		setLineColNum(absPath);
		maze = new Maze(lineNum, colNum);
		maze.initFromTextFile(absPath);
	}

	public void loadLabyrinth(String absPath){
		setLabyrinthSize(700,700);
		editedHexagons.clear();

		MazeBox[][] boxes = maze.getMazeMatrix();
		for (int row = 0; row < lineNum; row++) {
            for (int col = 0; col < colNum; col++) {
                int x = (int) (labyrinthWidth / 8 + col * hexagonWidth);
                int y = (int) (labyrinthHeight / 8 + row * hexagonHeight * 3 / 4);
                if (row % 2 == 1) {
                    x += hexagonWidth / 2;
                }
                int[] xPoints = new int[]{ x, x + hexagonWidth / 2, x + hexagonWidth, x + hexagonWidth, x + hexagonWidth / 2, x };
                int[] yPoints = new int[]{ y, y + hexagonHeight / 4, y, y - hexagonHeight / 2, y - hexagonHeight * 3 / 4, y - hexagonHeight / 2};
				if (boxes[row][col].getType() == "Empty")
					setCurrentColor(emptyColor);
				if (boxes[row][col].getType() == "Wall")
					setCurrentColor(wallColor);	
				if (boxes[row][col].getType() == "Arrival"){
					setCurrentColor(destinationColor);
					endBox = boxes[row][col];
				}
				if (boxes[row][col].getType() == "Departure"){
					setCurrentColor(startColor);
					startBox = boxes[row][col];
				}
                initCurrentHexagon(xPoints,yPoints);
				registerCurrentHexagon();
            }
        }
	}

	public void saveToFile(String fileName){
		maze.saveToTextFile(fileName);
	}
	
	// creat a matrix of empty box
	public void creatLabyrinth(int lineNum, int colNum){ 
		setLabyrinthSize(700,700);
		editedHexagons.clear();

		// Calculate the size of each hexagon based on the size of the panel and the number of rows and columns
		setLineNum(lineNum);
		setColNum(colNum);
		maze = new Maze(lineNum,colNum);

		// Calculate the position of each hexagon based on the size of the panel and the number of rows and columns
		MazeBox[][] boxes = maze.getMazeMatrix();
		setCurrentColor(emptyColor); 
        for (int row = 0; row < lineNum; row++) {
            for (int col = 0; col < colNum; col++) {
                int x = (int) (labyrinthWidth / 8 + col * hexagonWidth);
                int y = (int) (labyrinthHeight / 8 + row * hexagonHeight * 3 / 4);
                if (row % 2 == 1) {
                    x += hexagonWidth / 2;
                }
                int[] xPoints = new int[]{ x, x + hexagonWidth / 2, x + hexagonWidth, x + hexagonWidth, x + hexagonWidth / 2, x };
                int[] yPoints = new int[]{ y, y + hexagonHeight / 4, y, y - hexagonHeight / 2, y - hexagonHeight * 3 / 4, y - hexagonHeight / 2};
                initCurrentHexagon(xPoints,yPoints);
				registerCurrentHexagon();
				boxes[row][col] = new EmptyBox(row, col, maze);
            }
        }
	}

	// the methods delegated from several buttons of VIEW 

	public void runDijkstra(){  // get and paint the shortest path by running Dijkstra
		List<Vertex> shortestPath = Dijkstra.dijkstra(maze, startBox, endBox).getShortestPath(startBox, endBox);

		int len = shortestPath.size();
		for (int i=1;i<len-1;i++){
			currentHexagon = editedHexagons.get( shortestPath.get(i).getLine() * colNum + shortestPath.get(i).getColone() );
			modifyCurrentHexagon(pathColor);
			pathHexagons.add(currentHexagon);
		}
		hasPath = true;
	}

	public void erasePath(){
		for (Hexagon hex:pathHexagons){
			currentHexagon = hex;
			modifyCurrentHexagon(emptyColor);
		}
		pathHexagons.clear();
		hasPath = false;
	}

	public void setStart(){
		modifyCurrentHexagon(startColor);
		maze.getMazeMatrix()[currentBoxLineNo][currentBoxColNo] = new maze.DepartureBox(currentBoxLineNo, currentBoxColNo, maze);
		startBox = maze.getMazeMatrix()[currentBoxLineNo][currentBoxColNo];
		hasStart = true;
		setIsComplete();
	}

	public void setDestination(){
		modifyCurrentHexagon(destinationColor);
		maze.getMazeMatrix()[currentBoxLineNo][currentBoxColNo] = new maze.ArrivalBox(currentBoxLineNo, currentBoxColNo, maze);
		endBox = maze.getMazeMatrix()[currentBoxLineNo][currentBoxColNo];
		hasDestination = true;
		setIsComplete();
	}

	public void setEmpty(){
		modifyCurrentHexagon(emptyColor);
		maze.getMazeMatrix()[currentBoxLineNo][currentBoxColNo] = new maze.EmptyBox(currentBoxLineNo, currentBoxColNo, maze);
	}

	public void setWall(){
		modifyCurrentHexagon(wallColor);
		maze.getMazeMatrix()[currentBoxLineNo][currentBoxColNo] = new maze.WallBox(currentBoxLineNo, currentBoxColNo, maze);
	}



	// Operations on currentHexagon 

	public final void initCurrentHexagon(int[] xpoints, int[] ypoints){
		setcurrentHexagon(new Hexagon(xpoints, ypoints, currentColor));
	}

	public final boolean modifyCurrentHexagon(Color color){
		if (currentHexagon != null) {                   /*  if user tries to modify a uninit box, (ex: click Set Start Button 
															without selecting a box) we just ignore this event and return false	*/
			currentHexagon.setColor(color);
			setCurrentColor(color);
			modified = true;
			stateChanges();
			return true;
		}
		else
			return false;
	}

	public final void registerCurrentHexagon(){
		editedHexagons.add(currentHexagon);
		stateChanges();
	}

	public final void locateCurrentHexagon(int x, int y){
		int len = editedHexagons.size();
		for (int i = 0; i < len; i++){
			if (editedHexagons.get(i).contains(x,y)){
				currentHexagon = editedHexagons.get(i);
				currentBoxLineNo =  i / colNum;
				currentBoxColNo = i % colNum ;
			}
		}
	}

	public final void cancelCurrentHexagon(){
		setcurrentHexagon(null);
	}

	public final void paintHexagons(Graphics g) {
		for (Hexagon s : editedHexagons)
			s.paint(g);
		stateChanges();
	}



	// some simple setters and getters

	public final Color getCurrentColor() {
		return currentColor;
	}

	public final void setCurrentColor(Color currentColor) {
		if (this.currentColor != currentColor) {
			this.currentColor = currentColor;
			modified = true;
			stateChanges();
		}
	}

	// size of labyrinth
	public void setLineNum(int lineNum){
		this.lineNum = lineNum;
		setHexagonSize();
	} 

	public void setColNum(int colNum){
		this.colNum = colNum;
		setHexagonSize();
	}

	public void setLineColNum(String absPath){  // Parse the input file to set the line/column number of labyrinth
		int lineNum = 0, colNum = 0;
		try{
			BufferedReader in = Files.newBufferedReader(Paths.get(absPath));
			String str = null;
			while ((str = in.readLine()) != null){
				colNum = str.length();
				lineNum ++;
			}
		}
		catch (IOException ex){
            System.out.println("##an I/O error occurs: " + ex);
        }
		setLineNum(lineNum);
		setColNum(colNum);
	}

	public int getLineNum(){
		return lineNum;
	}

	public int getColNum(){
		return colNum;
	}

	public void setLabyrinthSize(int panelWidth, int panelHeight){
		labyrinthWidth = (int) (panelWidth * 0.8);
		labyrinthHeight = (int) (panelHeight * 0.8);
		setHexagonSize();
	}

	public final int getLabyrinthWidth(){
		return labyrinthWidth;
	}

	public final int getLabyrinthHeight(){
		return labyrinthHeight;
	}

	public final void setHexagonSize(){
		if (colNum == 0 || lineNum == 0)
			return;
		hexagonWidth = labyrinthWidth / colNum;
		if (labyrinthHeight / lineNum < hexagonWidth * 2 / Math.sqrt(3)){
			hexagonWidth = (int) (labyrinthHeight / lineNum / 2 * Math.sqrt(3));
		}
		hexagonHeight = (int) (hexagonWidth * 2 / Math.sqrt(3));
	}

	public final int getHexagonWidth(){
		return hexagonWidth;
	}

	public final int getHexagonHeight(){
		return hexagonHeight;
	}
	
	// currentHexagon 
	public final Hexagon getcurrentHexagon() {
		return currentHexagon;
	}

	public final void setcurrentHexagon(Hexagon currentHexagon) {
		if (this.currentHexagon != currentHexagon) {
			this.currentHexagon = currentHexagon;
			modified = true;
			stateChanges();
		}
	}
	
	public final boolean isModified() {
		return modified;
	}
	
	public final void setModified(boolean modified) {
		if (this.modified != modified)
			this.modified = modified;
	}

	public boolean getIsEmpty(){
		return editedHexagons.isEmpty();
	}

	public boolean getIsComplete(){
		return isComplete;
	}

	public boolean getHasPath(){
		return hasPath;
	}

	public void setIsComplete(){
		isComplete =  hasStart && hasDestination ;
	}

	public void clearEditedHexagons(){
		editedHexagons.clear();
	}
}
