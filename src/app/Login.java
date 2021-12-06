package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login extends Application{

	Scene scene;
	BorderPane borderPane;
	
	public void init() {
		borderPane = new BorderPane();
		scene =  new Scene(borderPane);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		init();
		stage.setScene(scene);
		stage.setTitle("Login Form");
		stage.setResizable(false);
		stage.show();
	}

}
