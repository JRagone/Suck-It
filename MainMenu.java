package application;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends Application {
	
	public GraphicsContext gc;
	public double screenWidth = Screen.getPrimary().getBounds().getWidth();
    public double screenHeight = Screen.getPrimary().getBounds().getHeight();
	private boolean startSelection = true;
	private static MediaPlayer mediaPlayer;
	
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage theStage){
		
		String path = "src/application/Edvard Grieg_ Peer Gynt - Morning Mood.mp3";
		Media media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(1.0);
		mediaPlayer.setAutoPlay(true);
		
		theStage.setTitle( "Timeline Example" );
    	theStage.setFullScreenExitHint("");
    	
    	Group root = new Group();

        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        theStage.setFullScreen(true);
        
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        root.getChildren().add( canvas );
		
        gc = canvas.getGraphicsContext2D();
        
        GridPane grid = new GridPane();

        Text title = new Text("PETER NORVING EXPLORATORIUM");
        title.setFont(Font.font ("MS PMincho", screenWidth/20));
        title.setX(screenWidth/2 - title.getLayoutBounds().getWidth()/2);
        title.setY(screenHeight/8);
        
        Text suckItText = new Text("Suck It!");
        suckItText.setFont(Font.font ("MS PMincho", screenWidth/25));
        suckItText.setX(screenWidth*.3 - suckItText.getLayoutBounds().getWidth()/2);
        suckItText.setY(screenHeight/3);
        
        Text aradText = new Text("Arad Adventure");
        aradText.setFont(Font.font ("MS PMincho", screenWidth/25));
        aradText.setX(screenWidth*.7 - aradText.getLayoutBounds().getWidth()/2);
        aradText.setY(screenHeight/3);
        
        
        root.getChildren().add(title);
        root.getChildren().add(suckItText);
        root.getChildren().add(aradText);
        
        Image suckItImage = new Image("file:SuckItScreenshot.png", screenWidth/2, screenHeight/2, true, false);
        gc.drawImage(suckItImage, screenWidth*.04, screenHeight*.4);
        
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
        
        Rectangle r = new Rectangle();
        r.setX(suckItText.getLayoutBounds().getMinX());
		r.setY(suckItText.getLayoutBounds().getMinY());
		r.setFill(Color.TRANSPARENT);
		r.setStroke(Color.BLACK);
		r.setWidth(suckItText.getLayoutBounds().getWidth());
		r.setHeight(suckItText.getLayoutBounds().getHeight());
		suckItText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				root.getChildren().add(r);
			}
		});
		r.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event){
				if(root.getChildren().contains(r)){
					root.getChildren().remove(r);
				}
			}
		});
		r.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
    			mediaPlayer.stop();
    			root.getChildren().remove(title);
    			root.getChildren().remove(suckItText);
    			root.getChildren().remove(aradText);
    			gc.clearRect(0, 0, screenWidth, screenHeight);
    			SuckIt suckIt = new SuckIt(screenWidth, screenHeight);
    			suckIt.start(root);
			}
		});		
		
        theStage.show();
        
	}
	
}
