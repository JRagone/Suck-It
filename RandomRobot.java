package application;

import java.util.ArrayList;
import java.util.List;

public class RandomRobot extends Robot {
	
	private RoomState room;
	private final int ROW = 0;
	private final int COL = 1;
	private int[] position = new int[2];
	
	public RandomRobot(RoomState start){
		room = start;
		position[0] = (int)(Math.random()*room.rowCount);
		position[1] = (int)(Math.random()*room.colCount);
		room.addRobot(this);
	}
	
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
	
	public RobotMove getMove(RoomState state){
		List<RobotMove> moves = generateMoves(state);
		if(moves.size()==0){
			return null;
		}
		int select = (int) (Math.random()*moves.size());
		return moves.get(select);
	}
	
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
