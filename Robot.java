package application;

import java.util.List;

public abstract class Robot {
	public abstract List<RobotMove> generateMoves(RoomState state);
	
	public abstract RobotMove getMove();
}
