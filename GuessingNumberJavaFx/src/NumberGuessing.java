
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NumberGuessing extends Application {
	
	private int guess = 0;
	private Button btn = new Button();
	private Text text2 = new Text();
	private TextField textf;
	private Text text; 
	int ran;
	boolean flag = false;
	private int count =10;
	private Text round;
	
	
	private void newGame()
	{
		//Reset properties for the new game
		ran = (int) Math.floor(Math.random()*100)+1;
		
		//text.setText(String.valueOf(ran));
		text2.setText("");
		text2.setStyle("-fx-font: 22 arial; -fx-base: #fff; -fx-margin-bottom: 50");
		text2.setFill(Color.ALICEBLUE);
		
		//button set up
		btn.setText("Guess");
		final String IDLE_BUTTON_STYLE = "-fx-background-color: rgba(0, 100, 100, 0.3); -fx-font: 25 arial; -fx-text-fill: #fff;";
		final String HOVERED_BUTTON_STYLE = "-fx-background-color: rgba(0, 100, 100, 0.5); -fx-font: 25 arial; -fx-text-fill: #fff;";


		btn.setStyle(IDLE_BUTTON_STYLE);
		btn.setOnMouseEntered(e -> btn.setStyle(HOVERED_BUTTON_STYLE));
		btn.setOnMouseExited(e -> btn.setStyle(IDLE_BUTTON_STYLE));
		//text field set up
		textf.setText("");
		textf.setStyle("-fx-font: 22 arial; -fx-base: #fff; -fx-background-radius: 10;");
		textf.setMaxWidth(600);
		textf.setPadding(new Insets(20,20,20,20));
		
		flag = false;
		count =10;
		round.setText("Attempt:"+count);
		round.setFont(Font.font("Verdana", FontWeight.NORMAL, 18));
		round.setFill(Color.ALICEBLUE);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//image
		primaryStage.setTitle("Welcome to Guess Number Game!");
		
		//text
		text = new Text("Please enter your guess number(from 1-100)");
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text.setFill(Color.ALICEBLUE);
		
		textf = new TextField();
		
		//Attempt
		round = new Text("Attempt:"+count);

		newGame();
		
		//Keyboard press Action Event
		textf.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER) {
				if(count>0) {
					count--;
				}
				
				round.setText("Attempt:"+count);
				
				try {
					
					if(flag) {
						newGame();
						return;
					}							
					checkNumber(textf.getText());
					textf.setText("");
					
				}
				catch(Exception ex) {
					text2.setText("Please enter your number");
					count++;
					round.setText("Attempt:"+count);
				}
			}
		});
		
		
		//Event handler for button
		btn.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent arg0) {
				if(count>0) {
					count--;
				}
				
				round.setText("Attempt:"+count);
				
				try {
					
					if(flag) {
						newGame();
						return;
					}	
				
					
					checkNumber(textf.getText());
				}
				catch(Exception e) {
					text2.setText("Please enter your number");
					count++;
					round.setText("Attempt:"+count);
				}
			}
			
		});
		
		//set up layout
		
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(30,100,30,100));
		pane.getChildren().add(round);
		pane.getChildren().add(text);
		pane.getChildren().add(text2);
		pane.getChildren().add(textf);
		pane.getChildren().add(btn);
		pane.setSpacing(10);
		pane.setStyle("-fx-background-color: rgba(0, 100, 100, 0.2);");
		VBox.setMargin(text2, new Insets(10,0,10,0));
		VBox.setMargin(textf, new Insets(0,0,30,0));
		
		Scene scene = new Scene(pane,820,850);
		File f = new File("src/bg_guessNum.png"); 							
		Image img = new Image(f.toURI().toString()); 										
		scene.setFill(new ImagePattern(img));
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//Check input number with random number
	public void checkNumber(String num) {
		guess = Integer.valueOf(num);
		
		//Compare numbers
		if(guess==ran) {
			text2.setText("Congratulations!! you win!!");
			btn.setText("Play again");
			flag = true;	
			
		}
		else if(count==0) {
			text2.setText("You used all of your attempts.");
			btn.setText("Play again");
			flag = true;	
		}
		else if(guess>ran) {
			text2.setText("Too high");
		}
		else if(guess<ran){
			text2.setText("Too low");
		}
		
		//Other case
		if(guess<1) {
			text2.setText("Please enter positive number");
			count++;
			round.setText("Attempt:"+count);
		}
		if(guess>100||guess==0) {
			text2.setText("Please enter number between 1-100");
			count++;
			round.setText("Attempt:"+count);
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
