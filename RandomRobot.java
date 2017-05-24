package application;

import java.util.List;

public class RandomRobot extends Robot {
	
	/*
	 * The RandomRobot simply generates a list of moves
	 * and randomly picks its next move from the list.
	 */
	
	public RandomRobot(RoomState start){
		super(start);
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
