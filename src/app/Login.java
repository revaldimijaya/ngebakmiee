package app;

import controller.UserController;
import helper.ErrorInfo;
import helper.Path;
import helper.SuccessInfo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;

public class Login extends Application implements EventHandler<ActionEvent>{

	Scene scene;
	BorderPane borderPane;
	GridPane gridPane;
	VBox verticalLayout;
	Label lblLogin, lblEmail, lblPass, lblToRegister;
	TextField txtEmail;
	PasswordField txtPass;
	Button btnLogin, btnRegister;
	
	public void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setVgap(15);
		gridPane.setHgap(20);
		gridPane.setAlignment(Pos.TOP_CENTER);
		scene =  new Scene(borderPane,500,250);
		
		verticalLayout = new VBox();
		verticalLayout.setAlignment(Pos.CENTER);
		verticalLayout.setSpacing(15);	
		
		lblLogin = new Label("Login");
		lblLogin.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 36));
		lblEmail = new Label("Email");
		lblPass = new Label("Password");
		lblToRegister = new Label("Register here !");
		lblToRegister.setTextFill(Color.BLUE);
		lblToRegister.setUnderline(true);
		txtEmail = new TextField();
		txtPass = new PasswordField();
		btnLogin = new Button("Login");
		btnLogin.setPadding(new Insets(5,20,5,20));
		btnRegister = new Button("Register");
		
//		add sub container
		Label header1 = new Label("");
		header1.setMaxHeight(1);
		Label header2 = new Label("");
		header2.setMinWidth(85);
		header2.setMaxHeight(1);
		Label header3 = new Label("");
		header3.setMinWidth(85);
		header3.setMaxHeight(1);
		
		gridPane.add(header1, 0, 0);
		gridPane.add(header2, 1, 0);
		gridPane.add(header3, 2, 0);
		
		gridPane.add(lblEmail, 0, 1);
		gridPane.add(txtEmail, 1, 1, 2, 1);
		gridPane.add(lblPass, 0, 2);
		gridPane.add(txtPass, 1, 2, 2, 1);
		
		verticalLayout.getChildren().add(btnLogin);
		verticalLayout.getChildren().add(lblToRegister);
		
//		add container
		borderPane.setTop(lblLogin);
		borderPane.setAlignment(lblLogin, Pos.CENTER);
		borderPane.setMargin(lblLogin, new Insets(10,0,0,0));
		borderPane.setCenter(gridPane);
		borderPane.setBottom(verticalLayout);
		borderPane.setAlignment(verticalLayout, Pos.TOP_CENTER);
		borderPane.setMargin(verticalLayout, new Insets(0,0,20,0));
		
		txtEmail.setText("revaldi.mijaya@mail.com");
		txtPass.setText("revaldi111");
		txtEmail.setText("daniel.mananta@mail.com");
		txtPass.setText("daniel123");
	}
	
	public void addAction() {
		btnLogin.setOnAction(this);
		lblToRegister.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				Stage s = (Stage)lblToRegister.getScene().getWindow();
				s.close();
				
				Stage next = new Stage();
				try {
					new Register().start(next);
				} catch (Exception e2) {
					
				}
			}
		});
		
	}
	
	public Login() {
		init();
		addAction();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.setTitle("NgeBakmiee");
		stage.setResizable(false);
		stage.getIcons().add(new Image("file:ngebakmiee.png"));
		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			if(txtEmail.getText().isEmpty() || txtPass.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setContentText(ErrorInfo.emptyCredential);
				alert.showAndWait();
				return;
			}
			User user = UserController.authUser(txtEmail.getText(), txtPass.getText());
			if (user == null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setContentText(ErrorInfo.wrongCredential);
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setContentText("Welcome "+user.getUsername()+" !");
				alert.showAndWait();
				
				UserController.currUser = user;
				
				Stage curr = (Stage) btnLogin.getScene().getWindow();
				curr.close();
				
				Stage next = new Stage();
				try {
					new HomePage().start(next);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		
	}

}
