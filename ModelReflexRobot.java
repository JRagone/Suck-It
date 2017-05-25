package application;

import java.util.List;

public class ModelReflexRobot extends SimpleReflexRobot {
	
	/*
	 * The ModelReflexRobot has all the sensors of the SimpleReflex,
	 * but it also contains an internal state, which keeps track of
	 * the world and where the robot is in it.
	 */
	
	private int[][] model;
	public final int UNVISITED = 4;
	
	public ModelReflexRobot(RoomState start){
		super(start);
		startModel(start);
		senseDirtMap(start);
	}
	
	public void startModel(RoomState start){
		model = new int[start.getRowCount()][start.getColCount()];
		for(int r = 0; r<start.getRowCount(); r++){
			for(int c = 0; c<start.getColCount(); c++){
				if(r==position[ROW] && c==position[COL]){
					model[r][c] = start.ROBOT;
				}
				else{
					model[r][c] = UNVISITED;
				}
			}
		}
	}
	
	public void senseDirtMap(RoomState state){
		if(position[ROW]+1<state.getRowCount() && state.getBoard()[position[ROW]+1][position[COL]]==state.DIRT){
			model[position[ROW]+1][position[COL]]=state.DIRT;
		}
		if(position[ROW]-1>=0 && state.getBoard()[position[ROW]-1][position[COL]]==state.DIRT){
			model[position[ROW]-1][position[COL]]=state.DIRT;
		}
		if(position[COL]+1<state.getColCount() && state.getBoard()[position[ROW]][position[COL]+1]==state.DIRT){
			model[position[ROW]][position[COL]+1]=state.DIRT;
		}
		if(position[COL]-1>=0 && state.getBoard()[position[ROW]][position[COL]-1]==state.DIRT){
			model[position[ROW]][position[COL]-1]=state.DIRT;
		}
	}
	
	@Override
	public void applyMove(RobotMove move){
		model[position[ROW]][position[COL]]=0;
		super.applyMove(move);
		model[position[ROW]][position[COL]]=2;
	}
	
	public int[] findClosest(RoomState state, int type){
		
		int[] bestPos = null;						//Best position to be returned
		int[] Pos1 = null;							//Test position for comparison
		double Dist1 = Double.POSITIVE_INFINITY;	//Test distance for comparison
		int[] Pos2 = null;
		double Dist2 = Double.POSITIVE_INFINITY;
		
		for(int r = position[ROW]; r>=0; r--){
			for(int c = position[COL]; c<state.getColCount(); c++){
				if(model[r][c]==type){
					Pos1 = new int[2];
					Pos1[ROW] = r;
					Pos1[COL] = c;
					break;
				}
			}
		}
		for(int r = position[ROW]; r<state.getRowCount(); r++){
			for(int c = position[COL]; c>=0; c--){
				if(model[r][c]==type){
					Pos2 = new int[2];
					Pos2[ROW] = r;
					Pos2[COL] = c;
					break;
				}
			}
		}
		if(Pos1!=null){
			Dist1 = Math.pow(position[ROW] - Pos1[ROW], 2) + Math.pow(position[ROW] - Pos1[COL], 2);
		}
		if(Pos2!=null){
			Dist2 = Math.pow(position[ROW] - Pos2[ROW], 2) + Math.pow(position[ROW] - Pos2[COL], 2);
		}
		if(Math.min(Dist1, Dist2)==Dist1){
			bestPos = Pos1;
		}
		else{
			bestPos = Pos2;
		}
		return bestPos;
	}
	
	@Override
	public RobotMove getMove(RoomState state) {
		senseDirtMap(state);
		List<RobotMove> moves = generateMoves(state);
		if(moves.size()==0){	//Checks for empty list
			return null;
		}
		RobotMove closeMove = null;
		double closestMoveDist = Double.POSITIVE_INFINITY;
		int[] closestDirt = findClosest(state, state.DIRT);
		if(closestDirt!=null){
			System.out.println("closestDirt not null");
			for(RobotMove move : moves){
				double tmpDist = Math.pow(move.x - closestDirt[ROW], 2) + Math.pow(move.y - closestDirt[COL], 2);
				if(Math.min(closestMoveDist, tmpDist)==tmpDist){
					closestMoveDist = tmpDist;
					closeMove = move;
				}
			}
			System.out.println("made it");
			return closeMove;
		}
		int[] closestUnvisited = findClosest(state, UNVISITED);
		if(closestUnvisited!=null){
			for(RobotMove move : moves){
				double tmpDist = Math.pow(move.x - closestUnvisited[ROW], 2) + Math.pow(move.y - closestUnvisited[COL], 2);
				if(Math.min(closestMoveDist, tmpDist)==tmpDist){
					closestMoveDist = tmpDist;
					closeMove = move;
				}
			}
		}
		System.out.println(printModel());
		return closeMove;
	}
	
	public String printModel(){
		String out = "";
		for(int r = 0; r < model.length; r++){
			for(int c = 0; c < model[0].length; c++){
				out+=model[r][c];
			}
			out+="\n";
		}
		return out;
	}

}
