import app.HomePage;
import app.Login;
import app.Register;
import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
//		User user = UserController.authUser("daniel.mananta@mail.com", "daniel123");
//		UserController.currUser = user;
		Stage next = new Stage();
		try {
			new Login().start(next);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
