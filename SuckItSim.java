package application;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
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
		//mediaPlayer.setAutoPlay(true);
	}
    
	@Override
	public void start(Stage theStage){
		
		music();
		
		theStage.setTitle( "Timeline Example" );
    	theStage.setFullScreenExitHint("");
    	
    	Group root = new Group();
    	
    	RoomState leftStart = new RoomState();
    	RoomState rightStart = leftStart.clone();
    	
    	Robot leftRobot = new ModelReflexRobot(leftStart);
    	Robot rightRobot = new SimpleReflexRobot(rightStart);
    	
    	Timeline leftTimeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> leftDrawMove(leftStart, root, leftRobot)));
        leftTimeline.setCycleCount(Animation.INDEFINITE);
        leftTimeline.play();
        
        Timeline rightTimeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> rightDrawMove(rightStart, root, rightRobot)));
        rightTimeline.setCycleCount(Animation.INDEFINITE);
        rightTimeline.play();
    	
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
        
        final ComboBox priorityComboBox = new ComboBox();
        priorityComboBox.getItems().addAll(
            "Simple Reflex Robot",
            "Random Robot",
            "Model-Based Reflex Robot"
        );   

        priorityComboBox.setLayoutX(screenWidth/2);
        
        root.getChildren().add(priorityComboBox);
        
        theStage.show();
        
	}
	
	public void leftDrawMove(RoomState start, Group root, Robot robot){
        start.drawState(root, screenWidth/2, screenHeight, 0);
       	start.moveRobot(robot);
	}
	
	public void rightDrawMove(RoomState start, Group root, Robot robot){
        start.drawState(root, screenWidth/2, screenHeight, screenWidth/2);
       	start.moveRobot(robot);
	}
	
}
