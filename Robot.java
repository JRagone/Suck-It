package application;

import java.util.ArrayList;
import java.util.List;

public abstract class Robot {
	
	//Inherited Attributes
	protected final int ROW = 0;
	protected final int COL = 1;
	protected int[] position = new int[2];
	
	public Robot(RoomState start){
		position[0] = (int)(Math.random()*start.rowCount);
		position[1] = (int)(Math.random()*start.colCount);
		start.addRobot(this);
	}
	
	//Creates a list of possible moves given a state
	public List<RobotMove> generateMoves(RoomState state){
		List<RobotMove> moves = new ArrayList<RobotMove>();
		if(position[ROW]+1<state.rowCount){
			moves.add(new RobotMove(position[ROW]+1, position[COL]));
		}
		if(position[ROW]-1>=0){
			moves.add(new RobotMove(position[ROW]-1, position[COL]));
		}
		if(position[COL]+1<state.colCount){
			moves.add(new RobotMove(position[ROW], position[COL]+1));
		}
		if(position[COL]-1>=0){
			moves.add(new RobotMove(position[ROW], position[COL]-1));
		}
		return moves;
	}
	
	public abstract RobotMove getMove(RoomState state);
	
	//Updates robot position attributes given a move
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
