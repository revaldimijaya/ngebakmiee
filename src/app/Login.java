package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	}
	
	public void addAction() {
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
		stage.setTitle("Login Form");
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
