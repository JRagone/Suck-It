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
	
	public void drawRoom(Group root, double width, double height){
		for (int row = 0; row < rowCount; row++) {
    		for(int col = 0; col < colCount; col++){
	    	    Rectangle r = new Rectangle();
	    	    r.setX(col * (width*.94)/colCount+width*.03);
	    	    r.setY(row * (height-width*.06)/rowCount+width*.03);
	    	    r.setWidth(width*.94/colCount);
	    	    r.setHeight((height-width*.03)/rowCount);
	    	    r.setFill(Color.RED);
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
