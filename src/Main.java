import app.Login;
import app.Register;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Stage next = new Stage();
		try {
			new Login().start(next);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
