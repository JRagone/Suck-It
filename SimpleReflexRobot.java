package application;

public class SimpleReflexRobot extends Robot {
	
	/*
	 * This SimpleReflexRobot is a robot that knows it's position and contains
	 * dirt sensors for adjacent (left, right, up, down) squares. However, since
	 * this agent does not have state, it behaves irrationally when it encounters
	 * the bounds of the room.
	 */
	
	public SimpleReflexRobot(RoomState start){
		super(start);
	}
	
	public RobotMove senseDirtMove(RoomState state){
		if(position[ROW]+1<state.getRowCount() && state.getBoard()[position[ROW]+1][position[COL]]==state.DIRT){
			return new RobotMove(position[ROW]+1, position[COL]);
		}
		if(position[ROW]-1>=0 && state.getBoard()[position[ROW]-1][position[COL]]==state.DIRT){
			return new RobotMove(position[ROW]-1, position[COL]);
		}
		if(position[COL]+1<state.getColCount() && state.getBoard()[position[ROW]][position[COL]+1]==state.DIRT){
			return new RobotMove(position[ROW], position[COL]+1);
		}
		if(position[COL]-1>=0 && state.getBoard()[position[ROW]][position[COL]-1]==state.DIRT){
			return new RobotMove(position[ROW], position[COL]-1);
		}
		return null;
	}
	
	//Uses condition-action rules to pick move
	@Override
	public RobotMove getMove(RoomState state) {
		RobotMove move = senseDirtMove(state);
		if(move!=null){
			return move;
		}
		if(position[COL]+1<state.getColCount()){						//Condition
			return new RobotMove(position[ROW], position[COL]+1);	//Action
		}
		else{
			return new RobotMove(position[ROW], position[COL]-1);
		}
	}

}
