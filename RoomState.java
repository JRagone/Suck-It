package application;

import javafx.scene.Group;

public class RoomState {
	private Room room;
	private int[][] board;
	private int rowCount;
	private int colCount;
	private final int DIRT = 1;
	private final int ROBOT = 2;
	RoomState(){
		room = new Room((int)Math.round(Math.random()*10+5), (int)Math.round(Math.random()*10+5));
		rowCount = room.getRows();
		colCount = room.getCols();
		board = new int[rowCount][colCount];
		addDirt();
		System.out.println(this);
	}
	
	public void addDirt(){
		for(int r = 0; r < rowCount; r++){
			for(int c = 0; c < colCount; c++){
				if(Math.round(Math.random()*9)%3==0){
					board[r][c] = DIRT;
				}
			}
		}
	}
	
	public void drawState(Group root, double w, double h){
		room.drawRoom(root, w, h);
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
