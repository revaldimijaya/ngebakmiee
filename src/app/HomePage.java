package app;

import controller.UserController;
import internal.BuyFoodInternal;
import internal.CartInternal;
import internal.HistoryTransactionInternal;
import internal.ManageFoodInternalForm;
import internal.ProfileInternal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;

public class HomePage extends Application implements EventHandler<ActionEvent> {

	Scene scene;
	Pane internalPane;
	BorderPane borderPane;
	MenuBar menuBar;
	Menu profile, manage, transaction;
	MenuItem profileEdit, profileLogOff, profileExit, manageFood, transactionBuyFood, transactionViewFood,
			transactionViewCart, transactionViewDetailFood;
	Label lblWelcome;

	public void init() {
		lblWelcome = new Label("Welcome, " + UserController.currUser.getUsername());
		lblWelcome.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 36));
		
		borderPane = new BorderPane();
		scene = new Scene(borderPane, 1000, 630);
		internalPane = new Pane();
		menuBar = new MenuBar();
		profile = new Menu("Profile");
		manage = new Menu("Manage");
		transaction = new Menu("Transaction");
		profileEdit = new MenuItem("Edit Profile");
		profileEdit.setOnAction(this);
		profileLogOff = new MenuItem("Log Off");
		profileLogOff.setOnAction(this);
		profileExit = new MenuItem("Exit");
		profileExit.setOnAction(this);
		manageFood = new MenuItem("Manage Food");
		manageFood.setOnAction(this);
		transactionBuyFood = new MenuItem("Buy Food");
		transactionBuyFood.setOnAction(this);
		transactionViewFood = new MenuItem("View Transaction History");
		transactionViewFood.setOnAction(this);
		transactionViewCart = new MenuItem("View Cart");
		transactionViewCart.setOnAction(this);

		if (UserController.currUser.getRole().equals("Admin")) {
			menuBar.getMenus().addAll(profile, manage);
		} else {
			menuBar.getMenus().addAll(profile, transaction);
		}
		profile.getItems().addAll(profileEdit, profileLogOff, profileExit);
		manage.getItems().addAll(manageFood);
		transaction.getItems().addAll(transactionBuyFood, transactionViewFood, transactionViewCart);
		Label lblCopyRight = new Label("Copyright \u00a9 2021 by NgeBakmiee");
		lblCopyRight.setPadding(new Insets(10, 0, 10, 0));
		lblCopyRight.setAlignment(Pos.CENTER);
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(lblCopyRight);
		borderPane.setBottom(hbox);

		borderPane.setTop(menuBar);
		borderPane.setCenter(internalPane);

	}

	public Window generateWindow(String title, int width, int height, Node node) {
		Window window = new Window(title);

		window.getLeftIcons().add(new MinimizeIcon(window));
		window.getRightIcons().add(new CloseIcon(window));
		window.setPrefSize(width, height);
		window.setResizableWindow(false);

		window.setLayoutX(scene.getWidth() / 2 - width / 2);
		window.setLayoutY((scene.getHeight() / 2 - height / 2) - 15);
		window.getContentPane().getChildren().add(node);

		return window;
	}

	public HomePage() {
		init();
		home();
	}
	
	public void home() {
		borderPane.setCenter(lblWelcome);
	}

	Stage currStage;

	@Override
	public void start(Stage stage) throws Exception {
		currStage = stage;
		currStage.setScene(scene);
		currStage.setTitle("NgeBakmiee");
		currStage.setResizable(false);
		stage.getIcons().add(new Image("file:ngebakmiee.png"));
		currStage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == profileEdit) {
			borderPane.setCenter(internalPane);
			internalPane.getChildren().clear();
			internalPane.getChildren().add(generateWindow("Edit Profile", 800, 550, new ProfileInternal()));

		} else if (e.getSource() == manageFood) {
			internalPane.getChildren().clear();
			internalPane.getChildren().add(generateWindow("Manage Food", 800, 550, new ManageFoodInternalForm()));
		} else if (e.getSource() == profileLogOff) {
			currStage.close();

			Stage next = new Stage();
			try {
				new Login().start(next);
			} catch (Exception e2) {

			}
		} else if (e.getSource() == profileExit) {
			currStage.close();
		} else if (e.getSource() == transactionBuyFood) {
			internalPane.getChildren().clear();
			internalPane.getChildren().add(generateWindow("Buy Food", 800, 550, new BuyFoodInternal()));
		} else if (e.getSource() == transactionViewCart) {
			internalPane.getChildren().clear();
			internalPane.getChildren().add(generateWindow("View Cart", 800, 550, new CartInternal()));
		} else if (e.getSource() == transactionViewFood) {
			internalPane.getChildren().clear();
			internalPane.getChildren()
					.add(generateWindow("View Transaction", 800, 550, new HistoryTransactionInternal()));
		}
	}

}
