package application;

public class RobotMove {
	int x, y;

	public RobotMove(int a_x, int a_y) {
		x = a_x;
		y = a_y;
	}

	public String toString() {
		return "Robot to " + x
				+ ", " + y;
	}
}
