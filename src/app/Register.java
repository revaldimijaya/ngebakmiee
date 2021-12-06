package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Register extends Application implements EventHandler<ActionEvent> {

	Scene scene;
	GridPane gridPane, titlePane;
	HBox horizontalLayout;
	BorderPane borderPane;
	Button btnRegister, btnBack;
	Label lblRegister, lblId, lblName, lblPassword, lblEmail, lblPhone, lblAddress, lblGender;
	TextField txtId, txtName, txtEmail;
	PasswordField txtPass;
	Spinner<Integer> spnPhone;
	TextArea txtAddress;
	RadioButton rbMale, rbFemale;
	ToggleGroup tgGender;

	public void init() {
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_CENTER);

		borderPane = new BorderPane();
		
		horizontalLayout = new HBox();

		titlePane = new GridPane();
		titlePane.setAlignment(Pos.CENTER);
		scene = new Scene(borderPane, 500, 600);
		lblRegister = new Label("Register");
		lblRegister.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 36));
		lblId = new Label("User ID");
		lblName = new Label("User Name");
		lblPassword = new Label("User Password");
		lblEmail = new Label("User Email");
		lblPhone = new Label("Phone Number");
		lblAddress = new Label("User Address");
		lblGender = new Label("User Gender");
		txtId = new TextField();
		txtName = new TextField();
		txtPass = new PasswordField();
		txtEmail = new TextField();
		spnPhone = new Spinner<>();
		spnPhone.getStyleClass().clear();
		spnPhone.setMaxWidth(200);
		spnPhone.setEditable(true);
		txtAddress = new TextArea();
		txtAddress.setMaxWidth(200);
		txtAddress.setMaxHeight(100);
		rbMale = new RadioButton("Male");
		rbFemale = new RadioButton("Female");
		tgGender = new ToggleGroup();
		rbMale.setToggleGroup(tgGender);
		rbFemale.setToggleGroup(tgGender);
		btnRegister = new Button("Register");
		btnRegister.setAlignment(Pos.CENTER);
		btnRegister.setPadding(new Insets(5, 10, 5, 10));
		btnBack = new Button("Back");
		btnBack.setAlignment(Pos.CENTER);
		btnBack.setPadding(new Insets(5, 10, 5, 10));
//		add sub container
//		col, row, colspan, rowspan
		gridPane.setVgap(25);
		gridPane.setHgap(20);
		Label lblHeader1 = new Label("");
//		lblHeader1.setMinWidth(85);
		Label lblHeader2 = new Label("");
		lblHeader2.setMinWidth(85);
		Label lblHeader3 = new Label("");
		lblHeader3.setMinWidth(85);

		gridPane.add(lblHeader1, 0, 0);
		gridPane.add(lblHeader2, 1, 0);
		gridPane.add(lblHeader3, 2, 0);

		gridPane.add(lblId, 0, 1);
		gridPane.add(txtId, 1, 1, 2, 1);
		gridPane.add(lblName, 0, 2);
		gridPane.add(txtName, 1, 2, 2, 1);
		gridPane.add(lblPassword, 0, 3);
		gridPane.add(txtPass, 1, 3, 2, 1);
		gridPane.add(lblEmail, 0, 4);
		gridPane.add(txtEmail, 1, 4, 2, 1);
		gridPane.add(lblPhone, 0, 5);
		gridPane.add(spnPhone, 1, 5, 2, 1);
		gridPane.add(lblGender, 0, 6);
		gridPane.add(rbMale, 1, 6);
		gridPane.add(rbFemale, 2, 6);
		gridPane.add(lblAddress, 0, 7);
		gridPane.add(txtAddress, 1, 7, 2, 1);

		horizontalLayout.getChildren().add(btnRegister);
		horizontalLayout.getChildren().add(btnBack);
		horizontalLayout.setAlignment(Pos.CENTER);
		horizontalLayout.setSpacing(20);
		
//		add to container

		borderPane.setTop(lblRegister);
		borderPane.setAlignment(lblRegister, Pos.CENTER);
		borderPane.setMargin(lblRegister, new Insets(20,0,0,0));
		borderPane.setCenter(gridPane);
		borderPane.setAlignment(gridPane, Pos.TOP_CENTER);
		borderPane.setBottom(horizontalLayout);
		borderPane.setAlignment(horizontalLayout, Pos.TOP_CENTER);
		borderPane.setMargin(horizontalLayout, new Insets(0, 0, 50, 0));

	}
	
	void addAction() {
		btnBack.setOnAction(this);
		btnRegister.setOnAction(this);
	}

	public Register() {
		init();
		addAction();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.setTitle("Register Form");
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == btnBack) {
			Stage s = (Stage)btnBack.getScene().getWindow();
			s.close();
			
			Stage next = new Stage();
			try {
				new Login().start(next);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		} else if(e.getSource() == btnRegister) {
			
		}
	}

}
