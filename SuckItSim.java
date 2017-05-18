package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SuckItSim extends Application {
	
	public GraphicsContext gc;
	public double screenWidth = Screen.getPrimary().getBounds().getWidth();
    public double screenHeight = Screen.getPrimary().getBounds().getHeight();
	
    public static void main(String[] args) {
        launch(args);
    }
    
	@Override
	public void start(Stage theStage){
		theStage.setTitle( "Timeline Example" );
    	theStage.setFullScreenExitHint("");
    	
    	Group root = new Group();
    	Room room = new Room((int)Math.round(Math.random()*10+1), (int)Math.round(Math.random()*10+1));
    	room.drawRoom(root, screenWidth, screenHeight);
    	
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        theStage.setFullScreen(true);
        
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        root.getChildren().add( canvas );
		
        gc = canvas.getGraphicsContext2D();
        
      //Handles key events
        theScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent evt) {
            	//Use if statements to check keyCode
                if (evt.getCode().equals(KeyCode.ESCAPE)) {
                    theStage.close();
                }
            }
        });
        
        
        
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                
            }
        }.start();
        
        theStage.show();
        
	}
}
