package application;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SuckItSim extends Application {
	
	public GraphicsContext gc;
	public double screenWidth = Screen.getPrimary().getBounds().getWidth();
    public double screenHeight = Screen.getPrimary().getBounds().getHeight();
	
    public static void main(String[] args) {
        launch(args);
    }
    
    private static MediaPlayer mediaPlayer;
    
    public void music(){
		String path = "src/application/2 Unlimited - Get Ready For This.mp3";
		Media media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
	}
    
	@Override
	public void start(Stage theStage){
		
		music();
		
		theStage.setTitle( "Timeline Example" );
    	theStage.setFullScreenExitHint("");
    	
    	Group root = new Group();
    	RoomState start = new RoomState();
    	
    	ModelReflexRobot robot = new ModelReflexRobot(start);
    	Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> doSomething(start, root, robot)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    	
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
        
        
        
        
                   	
        
        theStage.show();
        
	}
	
	public void doSomething(RoomState start, Group root, ModelReflexRobot robot){
		System.out.println(robot.printModel());
        start.drawState(root, screenWidth, screenHeight);
       	start.moveRobot(robot);
	}
	
}
