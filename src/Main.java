import app.Register;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Stage registerStage = new Stage();
		try {
			new Register().start(registerStage);
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

}
