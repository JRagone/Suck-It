package application;

import java.io.File;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SuckIt {
	
	private static MediaPlayer mediaPlayer;
	private double screenWidth;
	private double screenHeight;
	private boolean startSelection = true;
	
	public SuckIt(double w, double h){
		screenWidth = w;
		screenHeight = h;
	}
	
	public void start(Group root){
		music();
		
		RoomState leftStart = new RoomState();
    	RoomState rightStart = leftStart.clone();
		
        final ComboBox leftComboBox = new ComboBox();
        leftComboBox.getItems().addAll(
            "Simple Reflex Robot",
            "Random Robot",
            "Model-Based Reflex Robot"
        );   

        leftComboBox.setValue("Select Robot");
        leftComboBox.setLayoutX(screenWidth/4 - leftComboBox.getWidth()/2);
        leftComboBox.setLayoutY(screenHeight/2);
		root.getChildren().add(leftComboBox);
        
        final ComboBox rightComboBox = new ComboBox();
        rightComboBox.getItems().addAll(
            "Simple Reflex Robot",
            "Random Robot",
            "Model-Based Reflex Robot"
        );   

        rightComboBox.setValue("Select Robot");
        rightComboBox.setLayoutX(screenWidth/4*3 - rightComboBox.getWidth()/2);
        rightComboBox.setLayoutY(screenHeight/2);
        
		root.getChildren().add(rightComboBox);
		
		
		
		new AnimationTimer(){
			
			Timeline leftTimeline;
			Timeline rightTimeline;
			
        	@Override
        	public void handle(long now){
        		if(!leftComboBox.getSelectionModel().isEmpty() && !rightComboBox.getSelectionModel().isEmpty()){
        			if(startSelection){
        				Robot leftRobot;
        				Robot rightRobot;
        				
        				if(leftComboBox.getValue().equals(leftComboBox.getItems().get(0))){
        					leftRobot = new SimpleReflexRobot(leftStart);
        				}
        				else if(leftComboBox.getValue().equals(leftComboBox.getItems().get(1))){
        					leftRobot = new RandomRobot(leftStart);
        				}
        				else{
        					leftRobot = new ModelReflexRobot(leftStart);
        				}
        				
        				if(rightComboBox.getValue().equals(rightComboBox.getItems().get(0))){
        					rightRobot = new SimpleReflexRobot(rightStart);
        				}
        				else if(rightComboBox.getValue().equals(rightComboBox.getItems().get(1))){
        					rightRobot = new RandomRobot(rightStart);
        				}
        				else{
        					rightRobot = new ModelReflexRobot(rightStart);
        				}
        				
        				leftTimeline = new Timeline(new KeyFrame(
        		                Duration.millis(1000),
        		                ae -> leftDrawMove(leftStart, root, leftRobot)));
        		        leftTimeline.setCycleCount(Animation.INDEFINITE);
        		        
        		        
        		        rightTimeline = new Timeline(new KeyFrame(
        		                Duration.millis(1000),
        		                ae -> rightDrawMove(rightStart, root, rightRobot)));
        		        rightTimeline.setCycleCount(Animation.INDEFINITE);
        		        
        				startSelection = false;
        			}
        			leftTimeline.play();
        			rightTimeline.play();
        		}
        	}
        }.start();
		
	}
	
	public void music(){
		String path = "src/application/2 Unlimited - Get Ready For This.mp3";
		Media media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(.5);
		mediaPlayer.setAutoPlay(true);
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
