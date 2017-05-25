package application;

import javafx.scene.Group;

public class RoomState {
	
	private Room room;
	private int[][] board;
	private int rowCount;
	private int colCount;
	public final int NOTHING = 0;
	public final int DIRT = 1;
	public final int ROBOT = 2;
	
	//Constructor
	RoomState(){
		room = new Room((int)Math.round(Math.random()*10+5), (int)Math.round(Math.random()*10+5)); //Creates instance of room
		rowCount = room.getRows();
		colCount = room.getCols();
		board = new int[rowCount][colCount];
		addDirt(); //Adds dirt randomly
		System.out.println(this);
	}
	
	//Takes a given robot and draws it in the room
	public void addRobot(Robot robot){
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				if(r==robot.getRow() && c==robot.getCol()){
					board[r][c] = ROBOT;
				}
			}
		}
	}
	
	//Takes a robot and calls getMove, then applies move
	public void moveRobot(Robot robot){
		RobotMove move = robot.getMove(this);
		if(move==null){	//Safety for NullPointerException
			return;
		}
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				if(r==robot.getRow() && c==robot.getCol()){
					board[r][c] = NOTHING;
				}
				else if(r==move.x && c==move.y){
					board[r][c] = ROBOT;
				}
			}
		}
		robot.applyMove(move);
	}
	
	//Randomly places dirt
	public void addDirt(){
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				if((int)(Math.random()*6+1)%6==0){
					board[r][c] = DIRT;
				}
			}
		}
	}
	
	//Visualizes current state
	public void drawState(Group root, double w, double h, double x){
		room.drawRoom(root, this, w, h, x);
	}
	
	//Clone room
	public RoomState clone(){
		RoomState clone = new RoomState();
		clone.board = new int[rowCount][colCount];
		clone.rowCount = rowCount;
		clone.colCount = colCount;
		for(int r = 0; r < clone.rowCount; r++){
			for(int c = 0; c < clone.colCount; c++){
				if(board[r][c]==DIRT){
					clone.board[r][c]=DIRT;
				}
				else{
					clone.board[r][c]=NOTHING;
				}
			}
		}
		clone.room = room;
		System.out.println(clone);
		return clone;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public int getRowCount(){
		return rowCount;
	}
	
	public int getColCount(){
		return colCount;
	}
	
	//Prints room
	@Override
	public String toString(){
		String out = "";
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				out+=board[r][c];
			}
			out+="\n";
		}
		return out;
	}
	
}
