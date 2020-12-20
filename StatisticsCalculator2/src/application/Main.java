package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("Main.fxml"));
		AnchorPane pane = loader.load();
		primaryStage.setScene(new Scene(pane));
		primaryStage.setTitle("Stats Calculator");
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}
	
	public static void main(String[] args) {
		launch(args);	
	}
	
}
