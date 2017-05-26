package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Room {
	
	private int rowCount;
	private int colCount;
	
	public Room(int r, int c){
		rowCount = r;
		colCount = c;
	}
	
	//Creates a random room within given bounds and according to given state
	public void drawRoom(Group root, RoomState state, double width, double height, double xOffset){
		for (int row = 0; row < rowCount; row++) {
    		for(int col = 0; col < colCount; col++) {
	    	    Rectangle r = new Rectangle();
	    	    r.setX(col * (width*.94)/colCount+width*.03 + xOffset); //width*.03 offsets for border and xOffset customizes horizontal position
	    	    r.setY(row * (height-width*.06)/rowCount+width*.03);
	    	    r.setWidth(width*.94/colCount);
	    	    r.setHeight((height-width*.03)/rowCount);
	    	    if(state.getBoard()[row][col]==state.DIRT){
	    	    	r.setFill(Color.TAN);
	    	    }
	    	    else if(state.getBoard()[row][col]==state.ROBOT){
	    	    	r.setFill(Color.BLUE);
	    	    }
	    	    else{
	    	    	r.setFill(Color.WHITE);
	    	    }
	    	    r.setStroke(Color.BLACK);
	    	    root.getChildren().add(r);
    		}
    	}
	}
	
	public int getRows(){
		return rowCount;
	}
	
	public int getCols(){
		return colCount;
	}
	
}
