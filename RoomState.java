package application;

import javafx.scene.Group;

public class RoomState {
	
	private Room room;
	int[][] board;
	public final int rowCount;
	public final int colCount;
	public final int NOTHING = 0;
	public final int DIRT = 1;
	public final int ROBOT = 2;
	
	RoomState(){
		room = new Room((int)Math.round(Math.random()*10+5), (int)Math.round(Math.random()*10+5));
		rowCount = room.getRows();
		colCount = room.getCols();
		board = new int[rowCount][colCount];
		addDirt();
		System.out.println(this);
	}
	
	public void addRobot(RandomRobot robot){
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				if(r==robot.getRow() && c==robot.getCol()){
					board[r][c] = ROBOT;
				}
			}
		}
	}
	
	public void addDirt(){
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				if((int)(Math.random()*6+1)%6==0){
					board[r][c] = DIRT;
				}
			}
		}
	}
	
	public void drawState(Group root, double w, double h){
		room.drawRoom(root, this, w, h);
	}
	
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
