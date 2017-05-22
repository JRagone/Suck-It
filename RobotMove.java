package application;

public class RobotMove {
	int player;
	int x, y;

	public RobotMove(int a_player, int a_x, int a_y) {
		player = a_player;
		x = a_x;
		y = a_y;
	}

	public String toString() {
		return "Robot to " + x
				+ ", " + y;
	}
}
