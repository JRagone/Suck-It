package application;

import java.util.List;

public class RandomRobot extends Robot {
	
	public RandomRobot(RoomState start){
		room = start;
		position[0] = (int)(Math.random()*room.rowCount);
		position[1] = (int)(Math.random()*room.colCount);
		room.addRobot(this);
	}
	
	//Randomly chooses move
	@Override
	public RobotMove getMove(RoomState state){
		List<RobotMove> moves = generateMoves(state);
		if(moves.size()==0){	//Checks for empty list
			return null;
		}
		int select = (int) (Math.random()*moves.size());
		return moves.get(select);
	}

}
