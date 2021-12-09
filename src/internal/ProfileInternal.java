package internal;

import app.Login;
import controller.UserController;
import helper.SuccessInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;

public class ProfileInternal extends BorderPane implements EventHandler<ActionEvent>{

	GridPane gridPane;
	Label lblName, lblEmail, lblPhone, lblAddress, lblGender, lblOldPass, lblNewPass, lblConfirmPass;
	TextField txtName, txtEmail, txtPhone;
	TextArea txtAddress;
	PasswordField txtOldPass, txtNewPass, txtConfirmPass;
	RadioButton rbMale, rbFemale;
	ToggleGroup tgGender;
	Button btnUpdateProfile, btnChangePassword;
	User user;
	
	public void init() {
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_CENTER);
		lblName = new Label("User Name");
		lblName.setPrefWidth(130);
		lblEmail = new Label("User Email");
		lblPhone = new Label("Phone Number");
		lblAddress = new Label("User Address");
		lblGender = new Label("User Gender");
		lblOldPass = new Label("Old Password");
		lblOldPass.setPrefWidth(130);
		lblNewPass = new Label("New Password");
		lblConfirmPass = new Label("Confirm Password");
		txtName = new TextField();
		txtEmail = new TextField();
		txtPhone = new TextField();
		txtAddress = new TextArea();
		txtAddress.setMaxWidth(Double.MAX_VALUE);
		txtAddress.setMaxHeight(100);
		txtOldPass = new PasswordField();
		txtOldPass.setPrefWidth(130);
		txtNewPass = new PasswordField();
		txtConfirmPass = new PasswordField();
		rbMale = new RadioButton("Male");
		rbFemale = new RadioButton("Female");
		tgGender = new ToggleGroup();
		rbMale.setToggleGroup(tgGender);
		rbFemale.setToggleGroup(tgGender);
		btnChangePassword = new Button("Change Password");
		btnChangePassword.setPrefWidth(180);
		btnChangePassword.setAlignment(Pos.CENTER);
		btnUpdateProfile = new Button("Update Profile");
		btnUpdateProfile.setAlignment(Pos.CENTER);
		btnUpdateProfile.setPrefWidth(180);
		
		gridPane.setVgap(20);
		gridPane.setHgap(20);
		Label lblHeader1 = new Label("");
//		lblHeader1.setMinWidth(85);
		Label lblHeader2 = new Label("");
		lblHeader2.setMinWidth(85);
		Label lblHeader3 = new Label("");
		lblHeader3.setMinWidth(85);
		
		Label lblHeader4 = new Label("");
		lblHeader4.setMinWidth(85);
		Label lblHeader5 = new Label("");
		lblHeader5.setMinWidth(85);
		Label lblHeader6 = new Label("");
		lblHeader6.setMinWidth(85);
		
		Label lblTitle1 = new Label("Update Profile");
		lblTitle1.setMaxWidth(Double.MAX_VALUE);
		lblTitle1.setAlignment(Pos.CENTER);
		lblTitle1.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 24));
		
		Label lblTitle2 = new Label("Change Password");
		lblTitle2.setMaxWidth(Double.MAX_VALUE);
		lblTitle2.setAlignment(Pos.CENTER);
		lblTitle2.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 24));

		gridPane.add(lblHeader1, 0, 0);
		gridPane.add(lblHeader2, 1, 0);
		gridPane.add(lblHeader3, 2, 0);
		
		gridPane.add(lblHeader4, 3, 0);
		gridPane.add(lblHeader5, 4, 0);
		gridPane.add(lblHeader6, 5, 0);
		
		gridPane.add(lblTitle1, 0, 1, 3, 1);
		gridPane.add(lblTitle2, 3, 1, 3, 1);
		gridPane.add(lblName, 0, 2);
		gridPane.add(txtName, 1, 2, 2, 1);
		gridPane.add(lblEmail, 0, 3);
		gridPane.add(txtEmail, 1, 3, 2, 1);
		gridPane.add(lblPhone, 0, 4);
		gridPane.add(txtPhone, 1, 4, 2, 1);
		gridPane.add(lblGender, 0, 5);
		gridPane.add(rbMale, 1, 5);
		gridPane.add(rbFemale, 2, 5);
		gridPane.add(lblAddress, 0, 6);
		gridPane.add(txtAddress, 1, 6, 2, 1);
		HBox hbox1 = new HBox();
		hbox1.getChildren().add(btnUpdateProfile);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setMaxWidth(Double.MAX_VALUE);
		gridPane.add(hbox1, 0, 7, 3, 1);
		
		gridPane.add(lblOldPass, 3, 2);
		gridPane.add(txtOldPass, 4, 2, 2,1);
		gridPane.add(lblNewPass, 3, 3);
		gridPane.add(txtNewPass, 4, 3, 2,1);
		gridPane.add(lblConfirmPass, 3, 4);
		gridPane.add(txtConfirmPass, 4, 4, 2,1);
		HBox hbox2 = new HBox();
		hbox2.getChildren().add(btnChangePassword);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setMaxWidth(Double.MAX_VALUE);
		gridPane.add(hbox2, 3, 7, 3, 1);
		setCenter(gridPane);
//		setPadding(new Insets(10));
	}
	
	public void setUpAction() {
		btnUpdateProfile.setOnAction(this);
		btnChangePassword.setOnAction(this);
	}
	
	public void generateProfile() {
		this.user = UserController.currUser;
		txtAddress.setText(user.getAddress());
		txtEmail.setText(user.getEmail());
		txtName.setText(user.getUsername());
		txtPhone.setText(user.getPhone());
		if(user.getGender().equals("Male")) {
			rbMale.setSelected(true);
		} else {
			rbFemale.setSelected(true);
		}
	}
	
	public ProfileInternal() {
		init();
		setUpAction();
		generateProfile();
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == btnUpdateProfile) {
			String userId = user.getId();
			String userName = txtName.getText();
			String userEmail = txtEmail.getText();
			String userGender = (rbMale.isSelected()) ? "Male" : "Female";
			String userAddress = txtAddress.getText();
			String userPhone = txtPhone.getText();

			String registered = UserController.updateUser(userId, userName, userEmail, userGender, userAddress, userPhone);
			if(registered.equals(SuccessInfo.successUpdateUser)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setContentText(SuccessInfo.successUpdateUser);
				alert.showAndWait();
				UserController.currUser.setUsername(userName);
				UserController.currUser.setEmail(userEmail);
				UserController.currUser.setGender(userGender);
				UserController.currUser.setAddress(userAddress);
				UserController.currUser.setPhone(userPhone);
				generateProfile();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Alert");
				alert.setContentText(registered);
				alert.showAndWait();
			}
		} else if(e.getSource() == btnChangePassword) {
			String oldPass = txtOldPass.getText();
			String newPass = txtNewPass.getText();
			String confirmPass = txtConfirmPass.getText();
			
			String msg = UserController.changePassword(oldPass, newPass, confirmPass);
			if(msg.equals(SuccessInfo.successChangePassword)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setContentText(msg);
				alert.showAndWait();
				
				txtOldPass.setText("");
				txtNewPass.setText("");
				txtConfirmPass.setText("");
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Alert");
				alert.setContentText(msg);
				alert.showAndWait();
			}
			
		}
			
	}

}
