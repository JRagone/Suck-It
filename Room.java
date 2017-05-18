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
    		for(int col = 0; col<colCount; col++){
	    	    Rectangle r = new Rectangle();
	    	    r.setX(col*width/colCount);
	    	    r.setY(row * height/rowCount);
	    	    r.setWidth(width/colCount);
	    	    r.setHeight(height/rowCount);
	    	    r.setFill(Color.RED);
	    	    r.setStroke(Color.BLACK);
	    	    root.getChildren().add(r);
    		}
    	}
	}
	
	
}
