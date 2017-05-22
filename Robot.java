package application;

import java.util.List;

public abstract class Robot {
	
	protected final int ROW = 0;
	protected final int COL = 1;
	private int[] position = new int[2];
	
	public abstract List<RobotMove> generateMoves(RoomState state);
	
	public abstract RobotMove getMove(RoomState state);
	
	
	public void applyMove(RobotMove move){
		position[ROW] = move.x;
		position[COL] = move.y;
	}
	
	public int getRow(){
		return position[ROW];
	}
	
	public int getCol(){
		return position[COL];
	}
	
}
