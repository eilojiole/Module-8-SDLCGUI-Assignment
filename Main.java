/*Uchenna Ilojiole
 * CEN 3024 - Software Development 1
 * November 22, 2023
 * Main.java
 * This is our Main System Class. In this class we will be able to define the users options.
 * In This class we will also be able to retrieve our users input as well. This is also the class
 * where we will be able to save and update our library file based on the previous selections made
 * by our users.
 */

package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;


public class Main extends Application {
	
	public void start(Stage primaryStage) {
		try {
			File tempFIle=new File("MainScreen.fxml");
			System.out.print(tempFIle.exists()); 
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
	        Parent content=fxmlLoader.load();
			Scene scene = new Scene(content,800,600);
			primaryStage.setTitle("LMS");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
